package ru.ekr.roulette.ui.utils.interaction

import androidx.compose.foundation.interaction.FocusInteraction
import androidx.compose.foundation.interaction.HoverInteraction
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

@Composable
fun rememberInteractionSourceAnimated(
    vararg keys: Any?,
    itemsColor: () -> List<IndicationActionColor> = { listOf() },
): InteractionSourceAnimated {
    val scope = rememberCoroutineScope()
    val indication = remember(*keys) {
        InteractionSourceAnimated(
            listItems = itemsColor.invoke(),
            coroutineScope = scope
        )
    }
    DisposableEffect(Unit) {
        onDispose {
            indication.onDetach()
        }
    }
    return  indication
}

@Stable
class InteractionSourceAnimated(
    private val listItems: List<IndicationActionColor>,
    private val coroutineScope: CoroutineScope,
) : MutableInteractionSource {
    val listItemsAnimation = listItems.map { mutableStateOf(it.defaultColor) }
    private val colors = mutableMapOf<Pair<Interaction, Int>, ColorAnimation>()

    override val interactions =
        MutableSharedFlow<Interaction>(
            extraBufferCapacity = 16,
            onBufferOverflow = BufferOverflow.DROP_OLDEST,
        )

    override suspend fun emit(interaction: Interaction) {
        interactions.emit(interaction)
        when (interaction) {
            is PressInteraction.Press -> addAnimatedColors(interaction)
            is HoverInteraction.Enter -> addAnimatedColors(interaction)
            is FocusInteraction.Focus -> addAnimatedColors(interaction)
            is PressInteraction.Release -> removeAnimatedColors(interaction.press)
            is PressInteraction.Cancel -> removeAnimatedColors(interaction.press)
            is HoverInteraction.Exit -> removeAnimatedColors(interaction.enter)
            is FocusInteraction.Unfocus -> removeAnimatedColors(interaction.focus)
        }
    }

    override fun tryEmit(interaction: Interaction): Boolean {
        return interactions.tryEmit(interaction)
    }

    private fun addAnimatedColors(interaction: Interaction) {
        colors.forEach { _, colorAnimate -> colorAnimate.finish() }

        listItemsAnimation.forEachIndexed { index, animatable ->

            val (targetColor, defaultColor) = when (interaction) {
                is PressInteraction.Press -> listItems[index].clickColor
                is HoverInteraction.Enter -> listItems[index].hoverColor
                is FocusInteraction.Focus -> listItems[index].clickColor
                else -> listItems[index].defaultColor
            } to listItems[index].defaultColor

            val colorAnimation = ColorAnimation(targetColor, defaultColor)

            colors[keyOf(interaction, index)] = colorAnimation

            coroutineScope.launch {
                try {
                    colorAnimation.animate {
                        animatable.value = it
                    }
                } finally {
                    colors.remove(keyOf(interaction, index))
                }
            }
        }
    }

    fun onDetach() {
        colors.forEach { _, anim -> anim.finish() }
        colors.clear()
    }

    private fun removeAnimatedColors(interaction: Interaction) {
        listItems.indices.forEach {
            colors[keyOf(interaction, it)]?.finish()
        }
    }

    private fun keyOf(interaction: Interaction, indext: Int) = interaction to indext

    override fun hashCode(): Int {
        var result = listItems.hashCode()
        result = 31 * result
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is InteractionSourceAnimated) return false
        if (listItems != other.listItems) return false
        return true
    }
}