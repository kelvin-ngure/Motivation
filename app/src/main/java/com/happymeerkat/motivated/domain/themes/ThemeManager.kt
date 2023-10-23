package com.happymeerkat.motivated.domain.themes

import androidx.compose.ui.graphics.Color
import com.happymeerkat.motivated.R
import com.happymeerkat.motivated.data.models.Theme
import com.happymeerkat.motivated.data.preferences.ThemePreferencesRepository
import kotlinx.coroutines.flow.Flow

class ThemeManager(
    private val repository: ThemePreferencesRepository
) {
    val themes = listOf(
        Theme(
            themeId = 1,
            themeType = ThemeType.CITIES,
            backgroundImage = R.drawable.image1,
            backgroundColor = null,
            fontColor = Color.White,
            fontId = R.font.montserrat_regular,
            awsKey = "nature1"
        ),
        Theme(
            themeId = 2,
            themeType = ThemeType.NATURE,
            backgroundImage = R.drawable.image2,
            backgroundColor = null,
            fontColor = Color.White,
            fontId = R.font.robotomono_regular,
            awsKey = "nature2"
        ),
        Theme(
            themeId = 3,
            themeType = ThemeType.DESERT,
            backgroundImage = R.drawable.image3,
            backgroundColor = null,
            fontColor = Color.White,
            fontId = R.font.open_sans_regular,
            awsKey = "nature3"
        ),
        Theme(
            themeId = 4,
            themeType = ThemeType.TEXTURE,
            backgroundImage = R.drawable.image4,
            backgroundColor = null,
            fontColor = Color.White,
            fontId = R.font.open_sans_regular,
            awsKey = "nature4"
        ),
        Theme(
            themeId = 5,
            themeType = ThemeType.TEXTURE,
            backgroundImage = R.drawable.image5,
            backgroundColor = null,
            fontColor = Color.White,
            fontId = R.font.open_sans_regular,
            awsKey = "nature5"
        ),
        Theme(
            themeId = 6,
            themeType = ThemeType.PAINTING,
            backgroundImage = R.drawable.image6,
            backgroundColor = null,
            fontColor = Color.White,
            fontId = R.font.open_sans_regular,
            awsKey = "nature6"
        ),
        Theme(
            themeId = 7,
            themeType = ThemeType.NATURE,
            backgroundImage = R.drawable.image7,
            backgroundColor = null,
            fontColor = Color.White,
            fontId = R.font.open_sans_regular,
            awsKey = "nature7"
        ),
        Theme(
            themeId = 8,
            themeType = ThemeType.TEXTURE,
            backgroundImage = R.drawable.image8,
            backgroundColor = null,
            fontColor = Color.White,
            fontId = R.font.open_sans_regular,
            awsKey = "nature8"
        ),
        Theme(
            themeId = 9,
            themeType = ThemeType.NATURE,
            backgroundImage = R.drawable.image9,
            backgroundColor = null,
            fontColor = Color.White,
            fontId = R.font.open_sans_regular,
            awsKey = "nature9"
        ),
        Theme(
            themeId = 10,
            themeType = ThemeType.SKY,
            backgroundImage = R.drawable.image10,
            backgroundColor = null,
            fontColor = Color.Black,
            fontId = R.font.open_sans_regular,
            awsKey = "nature10"
        ),
        Theme(
            themeId = 11,
            themeType = ThemeType.ROAD,
            backgroundImage = R.drawable.image11,
            backgroundColor = null,
            fontColor = Color.White,
            fontId = R.font.open_sans_regular,
            awsKey = "nature11"
        ),
        Theme(
            themeId = 12,
            themeType = ThemeType.NATURE,
            backgroundImage = R.drawable.image12,
            backgroundColor = null,
            fontColor = Color.White,
            fontId = R.font.montserrat_regular,
            awsKey = "cities1"
        ),
        Theme(
            themeId = 13,
            themeType = ThemeType.CITIES,
            backgroundImage = R.drawable.image13,
            backgroundColor = null,
            fontColor = Color.White,
            fontId = R.font.robotomono_regular,
            awsKey = "cities2"
        ),
        Theme(
            themeId = 14,
            themeType = ThemeType.SKY,
            backgroundImage = R.drawable.image14,
            backgroundColor = null,
            fontColor = Color.White,
            fontId = R.font.open_sans_regular,
            awsKey = "cities3"
        ),
        Theme(
            themeId = 15,
            themeType = ThemeType.SKY,
            backgroundImage = R.drawable.image15,
            backgroundColor = null,
            fontColor = Color.White,
            fontId = R.font.open_sans_regular,
            awsKey = "cities4"
        ),
        Theme(
            themeId = 16,
            themeType = ThemeType.TEXTURE,
            backgroundImage = R.drawable.image16,
            backgroundColor = null,
            fontColor = Color.White,
            fontId = R.font.open_sans_regular,
            awsKey = "cities5"
        ),
        Theme(
            themeId = 17,
            themeType = ThemeType.DESERT,
            backgroundImage = R.drawable.image17,
            backgroundColor = null,
            fontColor = Color.White,
            fontId = R.font.open_sans_regular,
            awsKey = "cities6"
        ),
        Theme(
            themeId = 18,
            themeType = ThemeType.TEXTURE,
            backgroundImage = R.drawable.image18,
            backgroundColor = null,
            fontColor = Color(0xff023047),
            fontId = R.font.open_sans_regular,
            awsKey = "cities7"
        ),
        Theme(
            themeId = 19,
            themeType = ThemeType.TEXTURE,
            backgroundImage = R.drawable.image19,
            backgroundColor = null,
            fontColor = Color.White,
            fontId = R.font.open_sans_regular,
            awsKey = "cities8"
        ),
        Theme(
            themeId = 20,
            themeType = ThemeType.DESERT,
            backgroundImage = R.drawable.image20,
            backgroundColor = null,
            fontColor = Color.White,
            fontId = R.font.open_sans_regular,
            awsKey = "cities9"
        ),
        Theme(
            themeId = 21,
            themeType = ThemeType.SKY,
            backgroundImage = R.drawable.image21,
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
        Theme(
            themeId = 30,
            themeType = ThemeType.MOUNTAIN,
            backgroundImage = R.drawable.image22,
            backgroundColor = null,
            fontColor = Color.White,
            fontId = R.font.open_sans_regular,
            awsKey = "cities10"
        ),
    )

    val currentThemeId : Flow<Int> =  repository.readThemePreference

    suspend fun changeTheme(theme: Theme) {
        repository.saveThemePreference(theme.themeId)
    }

}

enum class ThemeType {
    COLORS, NATURE, CITIES, SKY, ROAD, TEXTURE, MOUNTAIN, DESERT, PAINTING
}