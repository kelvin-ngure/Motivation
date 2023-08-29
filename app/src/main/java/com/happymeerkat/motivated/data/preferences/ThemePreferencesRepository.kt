package com.happymeerkat.motivated.data.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import com.happymeerkat.motivated.R
import com.happymeerkat.motivated.domain.themes.FontManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class ThemePreferencesRepository @Inject constructor (
    private val dataStore: DataStore<Preferences>
) {
    private companion object {
        val CURRENT_FONT = intPreferencesKey("current_font")
    }

    suspend fun saveFontPreference(fontIndexInFontList: Int) {
        dataStore.edit { preferences ->
            preferences[CURRENT_FONT] = fontIndexInFontList
        }

    }

    val readFontPreference: Flow<Int> = dataStore.data
        .catch {
            if(it is IOException) {
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            preferences[CURRENT_FONT] ?: 0
        }
}