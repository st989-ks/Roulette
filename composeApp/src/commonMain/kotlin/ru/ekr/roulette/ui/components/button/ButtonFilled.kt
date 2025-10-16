package ru.ekr.roulette.ui.components.button

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.semantics.Role
import ru.ekr.roulette.ui.components.text.Text
import ru.ekr.roulette.ui.utils.interaction.IndicationActionColor
import ru.ekr.roulette.ui.utils.interaction.rememberInteractionSourceAnimated

@Composable
fun ButtonFilled(
    modifier: Modifier,
    enabled: Boolean,
    text: CharSequence,
    style: ButtonFilledStyleToken,
    onClick: () -> Unit
) {
    val interaction = rememberInteractionSourceAnimated() {
        listOf(
            IndicationActionColor(
                clickColor = style.content.click,
                hoverColor = style.content.hover,
                defaultColor = style.content.default,
            ),
            IndicationActionColor(
                clickColor = style.container.click,
                hoverColor = style.container.hover,
                defaultColor = style.container.default,
            )
        )
    }

    Row(
        modifier = modifier
            .clip(style.shapes)
            .drawBehind {
                if (enabled) {
                    drawRect(interaction.listItemsAnimation[1].value)
                } else {
                    drawRect(style.container.disabled)
                }
            }
            .clickable(
                role = Role.Button,
                enabled = enabled,
                interactionSource = interaction,
                onClickLabel = text.toString(),
                onClick = onClick
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = text,
            color = if (enabled) {
                interaction.listItemsAnimation[0].value
            } else {
                style.content.disabled
            }
        )
    }
}

