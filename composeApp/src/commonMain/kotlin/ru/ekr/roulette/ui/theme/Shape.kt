package ru.ekr.roulette.ui.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp


val Shapes = ShapesScheme(
    extraLargeAll = RoundedCornerShape(28.0.dp),
    extraLargeTop = RoundedCornerShape(
            topStart = 28.0.dp,
            topEnd = 28.0.dp,
            bottomEnd = 0.0.dp,
            bottomStart = 0.0.dp
        ),

    largeAll = RoundedCornerShape(16.0.dp),
    largeEnd = RoundedCornerShape(
            topStart = 0.0.dp,
            topEnd = 16.0.dp,
            bottomEnd = 16.0.dp,
            bottomStart = 0.0.dp
        ),
    largeTop = RoundedCornerShape(
            topStart = 16.0.dp,
            topEnd = 16.0.dp,
            bottomEnd = 0.0.dp,
            bottomStart = 0.0.dp
        ),

    mediumAll = RoundedCornerShape(12.0.dp),

    smallAll = RoundedCornerShape(8.0.dp),

    extraSmallAll = RoundedCornerShape(4.0.dp),
    extraSmallTop =
        RoundedCornerShape(
            topStart = 4.0.dp,
            topEnd = 4.0.dp,
            bottomEnd = 0.0.dp,
            bottomStart = 0.0.dp
        ),

    full = CircleShape,
    none = RectangleShape
)


@Immutable
data class ShapesScheme(
    val extraLargeAll: CornerBasedShape,
    val extraLargeTop: CornerBasedShape,
    val extraSmallAll: CornerBasedShape,
    val extraSmallTop: CornerBasedShape,
    val full: CornerBasedShape,
    val largeAll: CornerBasedShape,
    val largeEnd: CornerBasedShape,
    val largeTop: CornerBasedShape,
    val mediumAll: CornerBasedShape,
    val none: Shape,
    val smallAll: CornerBasedShape,
)