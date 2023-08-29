package com.happymeerkat.motivated.domain.themes

import com.happymeerkat.motivated.R
import com.happymeerkat.motivated.data.preferences.ThemePreferencesRepository
import kotlinx.coroutines.flow.Flow

class FontManager(
    repository: ThemePreferencesRepository
) {
    val fonts = listOf<Int>(
        R.font.montserrat_regular,
        R.font.open_sans_regular,
        R.font.robotomono_regular
    )

    val currentFontIndex : Flow<Int> =  repository.readFontPreference // reading pref returns 0 if no preference saved

    //val showCurrentFont: Flow<Int> = repository.readFontPreference
//        flow {
//
//            currentFontIndex.onEach {
//                Log.d("FONT STUFF", "font id is $it")
//                emit(fonts[it])
//            }
//        }
}