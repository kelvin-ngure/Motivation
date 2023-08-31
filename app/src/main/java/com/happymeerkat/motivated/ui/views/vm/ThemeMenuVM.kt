package com.happymeerkat.motivated.ui.views.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.happymeerkat.motivated.data.models.Theme
import com.happymeerkat.motivated.domain.themes.ThemeManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThemeMenuVM @Inject constructor(
    private val themeManager: ThemeManager
): ViewModel() {
    private val _themeMenuUI = MutableStateFlow(ThemeMenuUI())
    val themeMenuUI: StateFlow<ThemeMenuUI> = _themeMenuUI

    var currentThemeJob: Job? = null

    init {
        getThemes()
        getSelectedTheme()
    }

    fun getThemes() {
        _themeMenuUI.value = themeMenuUI.value.copy(
            themes = themeManager.themes
        )
    }

    fun getSelectedTheme() {
        currentThemeJob?.cancel()
        currentThemeJob = themeManager.currentThemeIndex.onEach { themeIndex ->
            _themeMenuUI.value = themeMenuUI.value.copy(
                selectedThemeIndex = themeIndex,
                selectedTheme = themeManager.themes[themeIndex]
            )
        }.launchIn(viewModelScope)
    }

    fun changeSelectedTheme(theme: Theme) {
        val themeIndex = themeManager.themes.indexOf(theme)
        _themeMenuUI.value = themeMenuUI.value.copy(
            selectedThemeIndex = themeIndex,
            selectedTheme = _themeMenuUI.value.themes[themeIndex]
        )
        viewModelScope.launch {
            themeManager.changeTheme(themeIndex)
        }
    }
}

data class ThemeMenuUI(
    val themes: List<Theme> = emptyList(),
    val selectedThemeIndex: Int = 0,
    val selectedTheme: Theme? = null
)