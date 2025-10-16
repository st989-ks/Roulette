package ru.ekr.roulette.ui.components.button

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape

data class ButtonFilledStyleToken(
    val shapes: Shape,
    val container: Container,
    val content: Content
) {
    data class Container(
        val default: Color,
        val disabled: Color,
        val click: Color,
        val hover: Color,
    )

    data class Content(
        val default: Color,
        val disabled: Color,
        val click: Color,
        val hover: Color,
    )
}
