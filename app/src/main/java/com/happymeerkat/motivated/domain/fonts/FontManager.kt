package com.happymeerkat.motivated.domain.fonts

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
       R.font.open_sans_regular,
        R.font.montserrat_regular,
        R.font.robotomono_regular,
        R.font.lato_regular,
        R.font.oswald_regular,
        R.font.merriweather_regular,
        R.font.prompt_regular,
        R.font.raleway_regular,
        R.font.worksans_regular
    )

    val currentThemeId : Flow<Int> =  repository.readThemePreference

    suspend fun changeTheme(theme: Theme) {
        repository.saveThemePreference(theme.themeId)
    }

}

enum class ThemeType {
    COLORS, NATURE, CITIES
}