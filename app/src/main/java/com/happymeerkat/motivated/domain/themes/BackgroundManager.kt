package com.happymeerkat.motivated.domain.themes

import androidx.compose.ui.graphics.Color
import com.happymeerkat.motivated.R
import com.happymeerkat.motivated.data.models.Theme
import com.happymeerkat.motivated.data.preferences.BackgroundPreferencesRepository
import kotlinx.coroutines.flow.Flow

class BackgroundManager (
    private val repository: BackgroundPreferencesRepository
) {
    val backgrounds = listOf(
        Theme(
            themeId = 0,
            backgroundImage = R.drawable.image1,
            backgroundColor = null,
            fontColor = Color.White
        ),
    )

    val currentBackgroundIndex : Flow<Int> =  repository.readBackgroundPreference // reading pref returns 0 if no preference saved. if 0 use theme background

    suspend fun changeBackground(backgroundIndex: Int) {
        repository.saveBackgroundPreference(backgroundIndex)
    }
}