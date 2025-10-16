package ru.ekr.roulette.ui.utils.ripple

import androidx.compose.foundation.IndicationNodeFactory
import androidx.compose.foundation.interaction.FocusInteraction
import androidx.compose.foundation.interaction.HoverInteraction
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.node.DelegatableNode
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.node.LayoutAwareModifierNode
import androidx.compose.ui.node.invalidateDraw
import androidx.compose.ui.node.requireDensity
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import kotlinx.coroutines.launch
import ru.ekr.roulette.ui.theme.LocalColors
import ru.ekr.roulette.ui.utils.interaction.ColorAnimation

@Composable
fun rememberRipple(
    vararg keys: Any?,
    colorRipple: Color = LocalColors.current.accentHover.copy(.25f),
): IndicationRipple {
    return remember(*keys, colorRipple) {
        IndicationRipple(
            colorRipple = colorRipple
        )
    }
}

class IndicationRipple(
    private val colorRipple: Color,
) : IndicationNodeFactory {

    override fun create(interactionSource: InteractionSource): DelegatableNode {
        return DelegateIndicationRippleNode(
            interactionSource = interactionSource,
            colorRipple = colorRipple,
        )
    }

    override fun hashCode(): Int {
        val result = 31 * colorRipple.hashCode()
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is IndicationRipple) return false
        if (colorRipple != other.colorRipple) return false
        return true
    }
}

open class DelegateIndicationRippleNode(
    private val interactionSource: InteractionSource,
    private val colorRipple: Color,
) : Modifier.Node(), DrawModifierNode, LayoutAwareModifierNode {

    private var targetRadius: Float = 0f
    private var rippleSize: Size = Size.Zero
    private val ripples = mutableMapOf<PressInteraction.Press, RippleAnimation>()

    override fun onRemeasured(size: IntSize) {
        val density = requireDensity()
        rippleSize = size.toSize()
        with(density) {
            val radiusCoveringBounds =
                (Offset(rippleSize.width, rippleSize.height).getDistance() / 2f)
            targetRadius = radiusCoveringBounds + 10.dp.toPx()
        }
    }

    override fun onAttach() {
        coroutineScope.launch {
            interactionSource.interactions.collect { interaction ->
                when (interaction) {
                    is PressInteraction.Press -> addRipple(interaction, targetRadius)
                    is PressInteraction.Release -> removeRipple(interaction.press)
                    is PressInteraction.Cancel -> removeRipple(interaction.press)
                    is HoverInteraction.Enter -> Unit
                    is FocusInteraction.Focus -> Unit
                    is HoverInteraction.Exit -> Unit
                    is FocusInteraction.Unfocus -> Unit
                }
            }
        }
    }

    private fun addRipple(interaction: PressInteraction.Press, targetRadius: Float) {
        ripples.forEach { _, ripple -> ripple.finish() }
        val origin = interaction.pressPosition
        val rippleAnimation = RippleAnimation(origin = origin, radius = targetRadius)
        ripples[interaction] = rippleAnimation
        coroutineScope.launch {
            try {
                rippleAnimation.animate()
            } finally {
                ripples.remove(interaction)
                invalidateDraw()
            }
        }
        invalidateDraw()
    }

    override fun ContentDrawScope.draw() {
        drawContent()
        if (colorRipple.alpha != 0f) {
            ripples.forEach { _, ripple -> with(ripple) { draw(colorRipple) } }
        }
    }

    private fun removeRipple(interaction: PressInteraction.Press) {
        ripples[interaction]?.finish()
    }

    override fun onDetach() {
        ripples.clear()
    }
}