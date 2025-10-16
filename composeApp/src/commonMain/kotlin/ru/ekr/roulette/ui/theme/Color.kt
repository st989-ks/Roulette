package ru.ekr.roulette.ui.theme

import androidx.compose.ui.graphics.Color


// DARK THEME — Dracula Dark Lime
val DarkMidnight = Color(0xFF12121A)
val CharcoalIndigo = Color(0xFF27273A)
val TransparentInk = Color(0x660F0F17)
val KiwiLime = Color(0xFF00FF00)
val MintCream = Color(0xFFC6FF92)
val SpringGreen = Color(0xFF7EDB47)
val GhostWhite = Color(0xFFF8F8F2)
val NickelGray = Color(0xFFB0B0B8)
val DimSilver = Color(0xFF666672)
val Apricot = Color(0xFFFFD8A8)
val Tangerine = Color(0xFFFFB870)
val RosePink = Color(0xFFFFB6B6)
val CoralRed = Color(0xFFFF7A7A)
val MintGreen = Color(0xFFC8FFC8)
val PastelGreen = Color(0xFF96E096)
val BabyBlue = Color(0xFFB0E0FF)
val LemonYellow = Color(0xFFF1FA8C)
val SpaceGray = Color(0xFF44475A)


// LIGHT THEME — Balanced Light Lime
val CloudWhite = Color(0xFFF9F9FB)
val PureWhite = Color(0xFFFFFFFF)
val TransparentSilver = Color(0x55DADADA)
val EmeraldGreen = Color(0xFF00AA00)
val SpringLime = Color(0xFF7EDB47)
val JetBlack = Color(0xFF1E1E1E)
val SteelGray = Color(0xFF5E5E64)
val CoolGray = Color(0xFF9E9EA5)
val CreamApricot = Color(0xFFFFF1D2)
val Caramel = Color(0xFFDA8C28)
val SoftPink = Color(0xFFFFE0E0)
val CrimsonRed = Color(0xFFD64545)
val MintMist = Color(0xFFD8FFD8)
val ForestGreen = Color(0xFF47B347)
val PowderBlue = Color(0xFFD0EEFF)
val PaleYellow = Color(0xFFFFFFA0)
val SkyBlue = Color(0xFFB0D4FF)


val DraculaDarkLimeScheme = ColorScheme(
    background = DarkMidnight,
    surface = CharcoalIndigo,
    overlay = TransparentInk,

    accent = KiwiLime,
    accentHover = MintCream,
    accentActive = SpringGreen,

    textPrimary = GhostWhite,
    textSecondary = NickelGray,
    textDisabled = DimSilver,

    warning = Apricot,
    warningText = Tangerine,
    error = RosePink,
    errorText = CoralRed,
    success = MintGreen,
    successText = PastelGreen,

    info = BabyBlue,
    highlight = LemonYellow,
    selection = SpaceGray
)

val LightLimeScheme = ColorScheme(
    background = CloudWhite,
    surface = PureWhite,
    overlay = TransparentSilver,

    accent = EmeraldGreen,
    accentHover = SpringLime,
    accentActive = KiwiLime,

    textPrimary = JetBlack,
    textSecondary = SteelGray,
    textDisabled = CoolGray,

    warning = CreamApricot,
    warningText = Caramel,
    error = SoftPink,
    errorText = CrimsonRed,
    success = MintMist,
    successText = ForestGreen,

    info = PowderBlue,
    highlight = PaleYellow,
    selection = SkyBlue
)


/**
 * Базовая цветовая схема приложения, не зависящая от Material.
 * Содержит основные, текстовые, акцентные и статусные цвета.
 *
 * @param background Главный фон. Темнее стандартного Dracula для глубокого контраста.
 * @param surface Поверхности (карточки, панели). Немного светлее фона.
 * @param overlay Полупрозрачный оверлей для модальных окон.
 * @param accent Кнопки, выделения, активные рамки.
 * @param accentHover Неактивный акцент.
 * @param accentActive Тёмный лайм при клике.
 * @param textPrimary Основной текст (как в Dracula).
 * @param textSecondary Вторичный текст, подписи.
 * @param textDisabled Неактивный текст.
 * @param warning Молочно-оранжевый для предупреждений.
 * @param warningText Текст и иконки предупреждений.
 * @param error Молочно-красный для ошибок.
 * @param errorText Текст и иконки ошибок.
 * @param success Нежно-зелёный успех.
 * @param successText Текст успеха.
 * @param info Для информационных сообщений.
 * @param highlight Подсветка текста.
 * @param selection Цвет выделения текста.
 */
data class ColorScheme(
    val background: Color,
    val surface: Color,
    val overlay: Color,
    val accent: Color,
    val accentHover: Color,
    val accentActive: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val textDisabled: Color,
    val warning: Color,
    val warningText: Color,
    val error: Color,
    val errorText: Color,
    val success: Color,
    val successText: Color,
    val info: Color,
    val highlight: Color,
    val selection: Color
)
