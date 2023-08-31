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
        )
    )

    val currentThemeIndex : Flow<Int> =  repository.readThemePreference // reading pref returns 0 if no preference saved

    suspend fun changeTheme(themeIndex: Int) {
        repository.saveThemePreference(themeIndex)
    }

}