package com.happymeerkat.motivated.data.models

import androidx.compose.ui.graphics.Color
import com.happymeerkat.motivated.domain.themes.ThemeManager

data class Theme(
    val themeId: Int,
    val backgroundImage: Int?,
    val backgroundColor: Color?,
    val fontColor: Color?,
    val fontId: Int?
)
