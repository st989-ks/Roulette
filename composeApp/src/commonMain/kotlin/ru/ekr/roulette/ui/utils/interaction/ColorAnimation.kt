package ru.ekr.roulette.ui.utils.interaction

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector4D
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

/**
 *  Анимация цветов.
 *
 * @param targetColor Целевой цвет.
 * @param defaultColor Дефолтный цвет
 * @property finishSignalDeferred Для ожидания завершения анимации перед началом исчезновения.
 * @property animatedAlpha Анимируемый переход между цветами.
 *
 */
internal class ColorAnimation(
    private val targetColor: Color,
    private val defaultColor: Color,
) {

    private val finishSignalDeferred = CompletableDeferred<Unit>(null)
    private val animatedAlpha: Animatable<Color, AnimationVector4D> = Animatable(defaultColor)

    /**
     * Запускает анимацию волны.
     */
    suspend fun animate(
        blockAnimatedColor: (Color) -> Unit,
    ) {
        fadeIn(blockAnimatedColor)
        finishSignalDeferred.await()
        fadeOut(blockAnimatedColor)
    }

    /**
     * Уменьшает прозрачность.
     */
    private suspend fun fadeIn(
        blockAnimatedColor: (Color) -> Unit,
    ) {
        coroutineScope {
            launch {
                animatedAlpha.animateTo(
                    targetValue = targetColor,
                    animationSpec = tween(
                        durationMillis = FadeInDuration, easing = LinearEasing
                    ),
                ) {
                    blockAnimatedColor(value)
                }
            }
        }
    }

    /**
     * Увеличивает прозрачность.
     */
    private suspend fun fadeOut(
        blockAnimatedColor: (Color) -> Unit,
    ) {
        coroutineScope {
            launch {
                animatedAlpha.animateTo(
                    targetValue = defaultColor,
                    animationSpec = tween(durationMillis = FadeOutDuration, easing = LinearEasing),
                ) {
                    blockAnimatedColor(value)
                }
            }
        }
    }

    /**
     * Завершает анимацию.
     */
    fun finish() {
        finishSignalDeferred.complete(Unit)
    }
}

private const val FadeInDuration = 75
private const val FadeOutDuration = 150
