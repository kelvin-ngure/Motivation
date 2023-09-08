package com.happymeerkat.motivated.domain.themes

import androidx.compose.ui.graphics.Color
import com.happymeerkat.motivated.R
import com.happymeerkat.motivated.data.models.Theme
import com.happymeerkat.motivated.data.preferences.ThemePreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class ThemeManager(
    private val repository: ThemePreferencesRepository
) {
    val themes = listOf(
        Theme(
            themeId = 1,
            themeType = ThemeType.NATURE,
            backgroundImage = R.drawable.nature1,
            backgroundColor = null,
            fontColor = Color.White,
            fontId = R.font.montserrat_regular,
            awsKey = "nature1"
        ),
        Theme(
            themeId = 2,
            themeType = ThemeType.NATURE,
            backgroundImage = R.drawable.nature2,
            backgroundColor = null,
            fontColor = Color.White,
            fontId = R.font.robotomono_regular,
            awsKey = "nature2"
        ),
        Theme(
            themeId = 3,
            themeType = ThemeType.NATURE,
            backgroundImage = R.drawable.nature3,
            backgroundColor = null,
            fontColor = Color.White,
            fontId = R.font.open_sans_regular,
            awsKey = "nature3"
        ),
        Theme(
            themeId = 4,
            themeType = ThemeType.NATURE,
            backgroundImage = R.drawable.nature4,
            backgroundColor = null,
            fontColor = Color.White,
            fontId = R.font.open_sans_regular,
            awsKey = "nature4"
        ),
        Theme(
            themeId = 5,
            themeType = ThemeType.NATURE,
            backgroundImage = R.drawable.nature5,
            backgroundColor = null,
            fontColor = Color.White,
            fontId = R.font.open_sans_regular,
            awsKey = "nature5"
        ),
        Theme(
            themeId = 6,
            themeType = ThemeType.NATURE,
            backgroundImage = R.drawable.nature6,
            backgroundColor = null,
            fontColor = Color.White,
            fontId = R.font.open_sans_regular,
            awsKey = "nature6"
        ),
        Theme(
            themeId = 7,
            themeType = ThemeType.NATURE,
            backgroundImage = R.drawable.nature7,
            backgroundColor = null,
            fontColor = Color.White,
            fontId = R.font.open_sans_regular,
            awsKey = "nature7"
        ),
        Theme(
            themeId = 8,
            themeType = ThemeType.NATURE,
            backgroundImage = R.drawable.nature8,
            backgroundColor = null,
            fontColor = Color.White,
            fontId = R.font.open_sans_regular,
            awsKey = "nature8"
        ),
        Theme(
            themeId = 9,
            themeType = ThemeType.NATURE,
            backgroundImage = R.drawable.nature9,
            backgroundColor = null,
            fontColor = Color.White,
            fontId = R.font.open_sans_regular,
            awsKey = "nature9"
        ),
        Theme(
            themeId = 10,
            themeType = ThemeType.NATURE,
            backgroundImage = R.drawable.nature10,
            backgroundColor = null,
            fontColor = Color.White,
            fontId = R.font.open_sans_regular,
            awsKey = "nature10"
        ),
        Theme(
            themeId = 11,
            themeType = ThemeType.NATURE,
            backgroundImage = R.drawable.nature11,
            backgroundColor = null,
            fontColor = Color.White,
            fontId = R.font.open_sans_regular,
            awsKey = "nature11"
        ),
        Theme(
            themeId = 12,
            themeType = ThemeType.CITIES,
            backgroundImage = R.drawable.cities1,
            backgroundColor = null,
            fontColor = Color.White,
            fontId = R.font.montserrat_regular,
            awsKey = "cities1"
        ),
        Theme(
            themeId = 13,
            themeType = ThemeType.CITIES,
            backgroundImage = R.drawable.cities2,
            backgroundColor = null,
            fontColor = Color.White,
            fontId = R.font.robotomono_regular,
            awsKey = "cities2"
        ),
        Theme(
            themeId = 14,
            themeType = ThemeType.CITIES,
            backgroundImage = R.drawable.cities3,
            backgroundColor = null,
            fontColor = Color.White,
            fontId = R.font.open_sans_regular,
            awsKey = "cities3"
        ),
        Theme(
            themeId = 15,
            themeType = ThemeType.CITIES,
            backgroundImage = R.drawable.cities4,
            backgroundColor = null,
            fontColor = Color.White,
            fontId = R.font.open_sans_regular,
            awsKey = "cities4"
        ),
        Theme(
            themeId = 16,
            themeType = ThemeType.CITIES,
            backgroundImage = R.drawable.cities5,
            backgroundColor = null,
            fontColor = Color.White,
            fontId = R.font.open_sans_regular,
            awsKey = "cities5"
        ),
        Theme(
            themeId = 17,
            themeType = ThemeType.CITIES,
            backgroundImage = R.drawable.cities6,
            backgroundColor = null,
            fontColor = Color.White,
            fontId = R.font.open_sans_regular,
            awsKey = "cities6"
        ),
        Theme(
            themeId = 18,
            themeType = ThemeType.CITIES,
            backgroundImage = R.drawable.cities7,
            backgroundColor = null,
            fontColor = Color.White,
            fontId = R.font.open_sans_regular,
            awsKey = "cities7"
        ),
        Theme(
            themeId = 19,
            themeType = ThemeType.CITIES,
            backgroundImage = R.drawable.cities8,
            backgroundColor = null,
            fontColor = Color.White,
            fontId = R.font.open_sans_regular,
            awsKey = "cities8"
        ),
        Theme(
            themeId = 20,
            themeType = ThemeType.CITIES,
            backgroundImage = R.drawable.cities9,
            backgroundColor = null,
            fontColor = Color.White,
            fontId = R.font.open_sans_regular,
            awsKey = "cities9"
        ),
        Theme(
            themeId = 21,
            themeType = ThemeType.CITIES,
            backgroundImage = R.drawable.cities10,
            backgroundColor = null,
            fontColor = Color.White,
            fontId = R.font.open_sans_regular,
            awsKey = "cities10"
        ),
        Theme(
            themeId = 22,
            themeType = ThemeType.COLORS,
            backgroundImage = null,
            backgroundColor = Color(0xfffefae0),
            fontColor = Color.Black,
            fontId = R.font.open_sans_regular,
            awsKey = null
        ),
        Theme(
            themeId = 23,
            themeType = ThemeType.COLORS,
            backgroundImage = null,
            backgroundColor = Color(0xff8ecae6),
            fontColor = Color(0xff023047),
            fontId = R.font.open_sans_regular,
            awsKey = null
        ),
        Theme(
            themeId = 24,
            themeType = ThemeType.COLORS,
            backgroundImage = null,
            backgroundColor = Color(0xffdda15e),
            fontColor = Color(0xff283618),
            fontId = R.font.open_sans_regular,
            awsKey = null
        ),
        Theme(
            themeId = 25,
            themeType = ThemeType.COLORS,
            backgroundImage = null,
            backgroundColor = Color(0xffFBEAEB),
            fontColor = Color(0xff2F3C7E),
            fontId = R.font.open_sans_regular,
            awsKey = null
        ),
        Theme(
            themeId = 26,
            themeType = ThemeType.COLORS,
            backgroundImage = null,
            backgroundColor = Color(0xff101820),
            fontColor = Color(0xffFEE715),
            fontId = R.font.open_sans_regular,
            awsKey = null
        ),
        Theme(
            themeId = 27,
            themeType = ThemeType.COLORS,
            backgroundImage = null,
            backgroundColor = Color(0xffF96167),
            fontColor = Color(0xffF9E795),
            fontId = R.font.open_sans_regular,
            awsKey = null
        ),
        Theme(
            themeId = 28,
            themeType = ThemeType.COLORS,
            backgroundImage = null,
            backgroundColor = Color(0xffCCF381),
            fontColor = Color(0xff4831D4),
            fontId = R.font.open_sans_regular,
            awsKey = null
        ),
        Theme(
            themeId = 29,
            themeType = ThemeType.COLORS,
            backgroundImage = null,
            backgroundColor = Color(0xff317773),
            fontColor = Color(0xffE2D1F9),
            fontId = R.font.open_sans_regular,
            awsKey = null
        ),
    )

    val currentThemeId : Flow<Int> =  repository.readThemePreference

    suspend fun changeTheme(theme: Theme) {
        repository.saveThemePreference(theme.themeId)
    }

}

enum class ThemeType {
    COLORS, NATURE, CITIES
}