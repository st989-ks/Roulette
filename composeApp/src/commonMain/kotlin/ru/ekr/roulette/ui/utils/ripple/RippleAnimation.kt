package ru.ekr.roulette.ui.utils.ripple

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.util.lerp
import kotlin.math.max
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

/**
 * Класс [RippleAnimation] для обработки анимации эффекта волны.
 *
 * Создан специально для упрощенной работы анимаций в [DelegateIndicationRippleNode].
 * Эффект волны начинается с точки касания и расширется до заданного радиуса.
 *
 * @param origin Позиция начала анимации. Если значение равно null, анимация начнется из центра целевого макета.
 * @param radius Максимальный радиус эффекта волны.
 * @property startRadius Начальный радиус волны. Вычисляется в методе [draw] на основе размеров целевого макета.
 * @property targetCenter Позиция центра волны после завершения анимации.
 * @property animatedAlpha Анимируемое значение прозрачности волны.
 * @property animatedRadiusPercent Анимируемое значение радиуса волны.
 * @property animatedCenterPercent Анимируемое значение смещения центра волны.
 * @property finishSignalDeferred Для ожидания завершения анимации перед началом исчезновения.
 * @property finishedFadingIn Анимация появления завершена.
 * @property finishRequested Запрошено немедленное завершение анимации.
 */
internal class RippleAnimation(
    private var origin: Offset?,
    private val radius: Float,
) {

    private var startRadius: Float? = null
    private var targetCenter: Offset? = null
    private val animatedAlpha = Animatable(EmptyAlpha)
    private val animatedRadiusPercent = Animatable(0f)
    private val animatedCenterPercent = Animatable(0f)
    private val finishSignalDeferred = CompletableDeferred<Unit>(null)
    private var finishedFadingIn by mutableStateOf(false)
    private var finishRequested by mutableStateOf(false)

    /**
     * Запускает анимацию волны.
     */
    suspend fun animate() {
        fadeIn()
        finishedFadingIn = true
        finishSignalDeferred.await()
        fadeOut()
    }

    /**
     * Выполняет появление волны, изменяя его прозрачность и радиус.
     */
    private suspend fun fadeIn() {
        coroutineScope {
            launch {
                animatedAlpha.animateTo(
                    targetValue = FullAlpha,
                    animationSpec = tween(
                        durationMillis = FadeInDuration,
                        easing = LinearEasing
                    ),
                )
                fadeOut()
            }
            launch {
                animatedRadiusPercent.animateTo(
                    targetValue = 1f,
                    animationSpec = tween(
                        durationMillis = RadiusDuration,
                        easing = FastOutSlowInEasing
                    ),
                )
            }
            launch {
                animatedCenterPercent.animateTo(
                    targetValue = 1f,
                    animationSpec = tween(
                        durationMillis = RadiusDuration,
                        easing = LinearEasing
                    ),
                )
            }
        }
    }

    /**
     * Выполняет исчезновение волны, уменьшая его прозрачность.
     */
    private suspend fun fadeOut() {
        coroutineScope {
            launch {
                animatedAlpha.animateTo(
                    targetValue = EmptyAlpha,
                    animationSpec = tween(durationMillis = FadeOutDuration, easing = LinearEasing),
                )
            }
        }
    }

    /**
     * Завершает анимацию.
     */
    fun finish() {
        finishRequested = true
        finishSignalDeferred.complete(Unit)
    }

    /**
     * Отрисовывает текущее состояние волны на холсте.
     *
     * @param color Цвет риппл.
     */
    fun DrawScope.draw(color: Color) {

        val startRadiusRef = startRadius ?: getRippleStartRadius(size)
            .also { startRadius = it }

        val originRef = origin ?: center
            .also { origin = it }

        val targetCenterRef = targetCenter ?: Offset(size.width / 2.0f, size.height / 2.0f)
            .also { targetCenter = it }

        val alpha = if (finishRequested && !finishedFadingIn) FullAlpha else animatedAlpha.value

        val radius = lerp(startRadiusRef, radius, animatedRadiusPercent.value)
        val centerOffset =
            Offset(
                lerp(originRef.x, targetCenterRef.x, animatedCenterPercent.value),
                lerp(originRef.y, targetCenterRef.y, animatedCenterPercent.value),
            )

        val modulatedColor = color.copy(alpha = color.alpha * alpha)

        clipRect { drawCircle(modulatedColor, radius, centerOffset) }
    }

    /**
     * Функция [getRippleStartRadius] вычисляет начальный радиус эффекта волны на основе размеров макета.
     *
     * @param size Размер макета.
     * @return Начальный радиус волны.
     */
    private fun getRippleStartRadius(size: Size) = max(size.width, size.height) * 0.3f
}

private const val FadeInDuration = 75
private const val RadiusDuration = 225
private const val FadeOutDuration = 150
private const val FullAlpha = 1f
private const val EmptyAlpha = 0f
