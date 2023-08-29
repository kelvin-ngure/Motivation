package com.happymeerkat.motivated.ui.theme

import androidx.compose.ui.graphics.Color

// Light
val LightGreen = Color(0xFF606c38)
val DarkGreen = Color(0xFF283618)
val LightSuede = Color(0xFFfefae0)
val LightBrown = Color(0xFFab9e79)

// Dark
val Black = Color(0xFF000000)
val DarkBlue = Color(0xFF14213d)
val Gold = Color(0xFFfca311)
val White = Color(0xFFf5f5f5)

// Dark options
val DarkBlueGreen = Color(0xff213555)

val ErrorRed = Color(0xffcf5132)




sealed class ThemeColors (
    val background: Color,
    val surface: Color,
    val primary: Color,
    val text: Color,
    val error: Color
) {
    object Dark : ThemeColors (
        background = Black,
        surface = DarkBlue,
        primary = Black,
        text = White,
        error = ErrorRed
    )
    object Light : ThemeColors (
        background = Color.White,
        surface = LightBrown,
        primary = LightSuede,
        text = DarkBlueGreen,
        error = ErrorRed
    )
}