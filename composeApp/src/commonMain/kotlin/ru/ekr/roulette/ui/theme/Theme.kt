package ru.ekr.roulette.ui.theme

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import ru.ekr.roulette.ui.utils.ripple.rememberRipple

@Composable
fun RouletteTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    contentWindowInsets: WindowInsets = WindowInsets.systemBars,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DraculaDarkLimeScheme else LightLimeScheme
    val selection = colorScheme.selection

    CompositionLocalProvider(
        LocalColors provides colorScheme,
        LocalIndication provides rememberRipple(),
        LocalTextSelectionColors provides remember(selection) {
            TextSelectionColors(
                handleColor = selection,
                backgroundColor = selection.copy(alpha = 0.4f),
            )
        },
        content = content
    )
}

val LocalColors = staticCompositionLocalOf { LightLimeScheme }