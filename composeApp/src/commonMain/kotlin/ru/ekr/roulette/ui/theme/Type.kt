package ru.ekr.roulette.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.sp


private val DefaultLineHeightStyle = LineHeightStyle(
    alignment = LineHeightStyle.Alignment.Center,
    trim = LineHeightStyle.Trim.None
)

private val DefaultTextStyle = TextStyle.Default.copy(
    fontFamily = FontFamily.SansSerif,
    platformStyle = null,
    lineHeightStyle = DefaultLineHeightStyle,
)

private val DefaultTextStyleNormal = DefaultTextStyle.copy(fontWeight = FontWeight.Normal)
private val DefaultTextStyleMedium = DefaultTextStyle.copy(fontWeight = FontWeight.Medium)

val Typography = TypographyScheme(
    displayLarge = DefaultTextStyleNormal.copy(
        fontSize = 57.sp,
        lineHeight = 64.0.sp,
        letterSpacing = -(0.2.sp),
    ),
    displayMedium = DefaultTextStyleNormal.copy(
        fontSize = 45.sp,
        lineHeight = 52.0.sp,
        letterSpacing = 0.0.sp,
    ),
    displaySmall = DefaultTextStyleNormal.copy(
        fontSize = 36.sp,
        lineHeight = 44.0.sp,
        letterSpacing = 0.0.sp,
    ),
    headlineLarge = DefaultTextStyleNormal.copy(
        fontSize = 32.sp,
        lineHeight = 40.0.sp,
        letterSpacing = 0.0.sp,
    ),
    headlineMedium = DefaultTextStyleNormal.copy(
        fontSize = 28.sp,
        lineHeight = 36.0.sp,
        letterSpacing = 0.0.sp,
    ),
    headlineSmall = DefaultTextStyleNormal.copy(
        fontSize = 24.sp,
        lineHeight = 32.0.sp,
        letterSpacing = 0.0.sp,
    ),
    titleLarge = DefaultTextStyleNormal.copy(
        fontSize = 22.sp,
        lineHeight = 28.0.sp,
        letterSpacing = 0.0.sp,
    ),
    titleMedium = DefaultTextStyleMedium.copy(
        fontSize = 16.sp,
        lineHeight = 24.0.sp,
        letterSpacing = 0.2.sp,
    ),
    titleSmall = DefaultTextStyleMedium.copy(
        fontSize = 14.sp,
        lineHeight = 20.0.sp,
        letterSpacing = 0.1.sp,
    ),
    bodyLarge = DefaultTextStyleNormal.copy(
        fontSize = 16.sp,
        lineHeight = 24.0.sp,
        letterSpacing = 0.5.sp,
    ),
    bodyMedium = DefaultTextStyleNormal.copy(
        fontSize = 14.sp,
        lineHeight = 20.0.sp,
        letterSpacing = 0.2.sp,
    ),
    bodySmall = DefaultTextStyleNormal.copy(
        fontSize = 12.sp,
        lineHeight = 16.0.sp,
        letterSpacing = 0.4.sp,
    ),
    labelLarge = DefaultTextStyleMedium.copy(
        fontSize = 14.sp,
        lineHeight = 20.0.sp,
        letterSpacing = 0.1.sp,
    ),
    labelMedium = DefaultTextStyleMedium.copy(
        fontSize = 12.sp,
        lineHeight = 16.0.sp,
        letterSpacing = 0.5.sp,
    ),
    labelSmall = DefaultTextStyleMedium.copy(
        fontSize = 11.sp,
        lineHeight = 16.0.sp,
        letterSpacing = 0.5.sp,
    )
)

@Immutable
data class TypographyScheme(
    val displayLarge: TextStyle,
    val displayMedium: TextStyle,
    val displaySmall: TextStyle,
    val headlineLarge: TextStyle,
    val headlineMedium: TextStyle,
    val headlineSmall: TextStyle,
    val titleLarge: TextStyle,
    val titleMedium: TextStyle,
    val titleSmall: TextStyle,
    val bodyLarge: TextStyle,
    val bodyMedium: TextStyle,
    val bodySmall: TextStyle,
    val labelLarge: TextStyle,
    val labelMedium: TextStyle,
    val labelSmall: TextStyle,
)