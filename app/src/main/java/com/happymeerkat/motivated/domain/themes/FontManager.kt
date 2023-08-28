package com.happymeerkat.motivated.domain.themes

import android.util.Log
import com.happymeerkat.motivated.R
import com.happymeerkat.motivated.data.preferences.ThemePreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach

class FontManager(
    repository: ThemePreferencesRepository
) {
    private val fonts = listOf<Int>(
        R.font.montserrat_regular,
        R.font.open_sans_regular
    )

    private val currentFontIndex : Flow<Int> =  repository.readFontPreference // reading pref returns 0 if no preference saved

    val showCurrentFont: Flow<Int> = repository.readFontPreference
//        flow {
//
//            currentFontIndex.onEach {
//                Log.d("FONT STUFF", "font id is $it")
//                emit(fonts[it])
//            }
//        }
}