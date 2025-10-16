package ru.ekr.roulette.ui.utils.interaction

import androidx.compose.ui.graphics.Color

/**
 * Класс для хранения цветов индикации различных действий.
 *  @param clickColor Цвет при клике.
 *  @param hoverColor Цвет при наведении курсора.
 *  @param defaultColor Цвет по умолчанию.
 */
class IndicationActionColor(
    val clickColor: Color = Color.Companion.Unspecified,
    val hoverColor: Color = Color.Companion.Unspecified,
    val defaultColor: Color = Color.Companion.Unspecified,
)