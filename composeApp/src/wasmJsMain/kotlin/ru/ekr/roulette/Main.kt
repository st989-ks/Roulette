package ru.ekr.roulette

import androidx.compose.foundation.text.BasicText
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(content = { BasicText(" ЖОПАЖОПАЖОПАЖОПАЖОПАЖОПА /nЖОПАЖОПА") })
}