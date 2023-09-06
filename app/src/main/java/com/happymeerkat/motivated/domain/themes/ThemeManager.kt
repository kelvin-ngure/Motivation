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
            backgroundImage = R.drawable.nature1,
            backgroundColor = null,
            fontColor = Color.White,
            fontId = R.font.montserrat_regular,
            awsKey = "nature1"
        ),
        Theme(
            themeId = 2,
            backgroundImage = R.drawable.nature2,
            backgroundColor = null,
            fontColor = Color.White,
            fontId = R.font.robotomono_regular,
            awsKey = "nature2"
        ),
        Theme(
            themeId = 3,
            backgroundImage = R.drawable.nature3,
            backgroundColor = null,
            fontColor = Color.White,
            fontId = R.font.open_sans_regular,
            awsKey = "nature3"
        ),
        Theme(
            themeId = 4,
            backgroundImage = R.drawable.nature4,
            backgroundColor = null,
            fontColor = Color.White,
            fontId = R.font.open_sans_regular,
            awsKey = "nature4"
        ),
        Theme(
            themeId = 5,
            backgroundImage = R.drawable.nature5,
            backgroundColor = null,
            fontColor = Color.White,
            fontId = R.font.open_sans_regular,
            awsKey = "nature5"
        ),
        Theme(
            themeId = 6,
            backgroundImage = R.drawable.nature6,
            backgroundColor = null,
            fontColor = Color.White,
            fontId = R.font.open_sans_regular,
            awsKey = "nature6"
        ),
        Theme(
            themeId = 7,
            backgroundImage = R.drawable.nature7,
            backgroundColor = null,
            fontColor = Color.White,
            fontId = R.font.open_sans_regular,
            awsKey = "nature7"
        ),
        Theme(
            themeId = 9,
            backgroundImage = R.drawable.nature9,
            backgroundColor = null,
            fontColor = Color.White,
            fontId = R.font.open_sans_regular,
            awsKey = "nature9"
        ),
        Theme(
            themeId = 10,
            backgroundImage = R.drawable.nature10,
            backgroundColor = null,
            fontColor = Color.White,
            fontId = R.font.open_sans_regular,
            awsKey = "nature10"
        ),
        Theme(
            themeId = 11,
            backgroundImage = R.drawable.nature11,
            backgroundColor = null,
            fontColor = Color.White,
            fontId = R.font.open_sans_regular,
            awsKey = "nature11"
        ),
        Theme(
            themeId = 12,
            backgroundImage = R.drawable.nature12,
            backgroundColor = null,
            fontColor = Color.White,
            fontId = R.font.open_sans_regular,
            awsKey = "nature12"
        ),
        Theme(
            themeId = 13,
            backgroundImage = R.drawable.nature13,
            backgroundColor = null,
            fontColor = Color.White,
            fontId = R.font.open_sans_regular,
            awsKey = "nature13"
        ),
        Theme(
            themeId = 14,
            backgroundImage = R.drawable.nature14,
            backgroundColor = null,
            fontColor = Color.White,
            fontId = R.font.open_sans_regular,
            awsKey = "nature14"
        ),
        Theme(
            themeId = 15,
            backgroundImage = R.drawable.nature15,
            backgroundColor = null,
            fontColor = Color.White,
            fontId = R.font.open_sans_regular,
            awsKey = "nature15"
        ),
        Theme(
            themeId = 16,
            backgroundImage = R.drawable.nature16,
            backgroundColor = null,
            fontColor = Color.White,
            fontId = R.font.open_sans_regular,
            awsKey = "nature16"
        ),
        Theme(
            themeId = 19,
            backgroundImage = null,
            backgroundColor = Color(0xfffefae0),
            fontColor = Color.Black,
            fontId = R.font.open_sans_regular,
            awsKey = null
        ),
        Theme(
            themeId = 20,
            backgroundImage = null,
            backgroundColor = Color(0xff8ecae6),
            fontColor = Color(0xff023047),
            fontId = R.font.open_sans_regular,
            awsKey = null
        ),
        Theme(
            themeId = 21,
            backgroundImage = null,
            backgroundColor = Color(0xffdda15e),
            fontColor = Color(0xff283618),
            fontId = R.font.open_sans_regular,
            awsKey = null
        ),
        Theme(
            themeId = 22,
            backgroundImage = null,
            backgroundColor = Color(0xff1b263b),
            fontColor = Color(0xffe0e1dd),
            fontId = R.font.open_sans_regular,
            awsKey = null
        ),
    )

    // the int is the index of the theme in the list
    private val themeGroups = hashMapOf<ThemeType, List<Int>>()
    fun getThemeGroups(): HashMap<ThemeType, List<Int>> {
        themeGroups[ThemeType.COLORS] = (15..18).toList()
        themeGroups[ThemeType.NATURE] = (0..14).toList()

        return themeGroups
    }
    val currentThemeIndex : Flow<Int> =  repository.readThemePreference // reading pref returns 0 if no preference saved

    suspend fun changeTheme(themeIndex: Int) {
        repository.saveThemePreference(themeIndex)
    }

}

enum class ThemeType {
    COLORS, NATURE
}