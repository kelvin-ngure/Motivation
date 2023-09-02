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
            backgroundImage = R.drawable.image1,
            backgroundColor = null,
            fontColor = Color.White,
            fontId = R.font.montserrat_regular
        ),
        Theme(
            themeId = 2,
            backgroundImage = R.drawable.image2,
            backgroundColor = null,
            fontColor = Color.White,
            fontId = R.font.robotomono_regular
        ),
        Theme(
            themeId = 3,
            backgroundImage = R.drawable.image3,
            backgroundColor = null,
            fontColor = Color.White,
            fontId = R.font.open_sans_regular
        ),
        Theme(
            themeId = 4,
            backgroundImage = null,
            backgroundColor = Color(0xfffefae0),
            fontColor = Color.Black,
            fontId = R.font.open_sans_regular
        ),
        Theme(
            themeId = 5,
            backgroundImage = null,
            backgroundColor = Color(0xff8ecae6),
            fontColor = Color(0xff023047),
            fontId = R.font.open_sans_regular
        ),
        Theme(
            themeId = 6,
            backgroundImage = null,
            backgroundColor = Color(0xffdda15e),
            fontColor = Color(0xff283618),
            fontId = R.font.open_sans_regular
        ),
        Theme(
            themeId = 7,
            backgroundImage = null,
            backgroundColor = Color(0xff1b263b),
            fontColor = Color(0xffe0e1dd),
            fontId = R.font.open_sans_regular
        ),
    )

    // the int is the index of the theme in the list
    private val themeGroups = hashMapOf<ThemeType, List<Int>>()
    fun getThemeGroups(): HashMap<ThemeType, List<Int>> {
        themeGroups[ThemeType.COLORS] = listOf(3,4,5,6)
        themeGroups[ThemeType.NATURE] = listOf(0,1,2)

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