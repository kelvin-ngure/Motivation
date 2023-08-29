package com.happymeerkat.motivated.ui.views.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.happymeerkat.motivated.domain.themes.FontManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FontMenuVM @Inject constructor(
    private val fontManager: FontManager
): ViewModel() {
    private val _fontMenuUI = MutableStateFlow(FontMenuUI())
    val fontMenuUI: StateFlow<FontMenuUI> = _fontMenuUI

    var currentFontJob: Job? = null

    init {
        getAllFonts()
        updateSelectedFont()
    }

    fun getAllFonts() {
        _fontMenuUI.value = fontMenuUI.value.copy(
            fonts = fontManager.fonts
        )
    }

    fun updateSelectedFont() {
        currentFontJob?.cancel()
        currentFontJob = fontManager.currentFontIndex.onEach { fontIndex ->
            _fontMenuUI.value = fontMenuUI.value.copy(
                selectedFontIndex = fontIndex,
                selectedFont = fontManager.fonts[fontIndex]
            )
        }.launchIn(viewModelScope)

    }
}

data class FontMenuUI(
    val fonts: List<Int> = emptyList(),
    val selectedFontIndex: Int = 0,
    val selectedFont: Int = 0
)