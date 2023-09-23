package com.happymeerkat.motivated.data.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class ThemePreferencesRepository @Inject constructor (
    private val dataStore: DataStore<Preferences>
) {
    private companion object {
        val CURRENT_THEME = intPreferencesKey("current_theme")
        val CURRENT_FONT = intPreferencesKey("current_font")
        val INTRO_SEEN = booleanPreferencesKey("intro_seen")
    }

    suspend fun saveThemePreference(themeIndexInThemeList: Int) {
        dataStore.edit { preferences ->
            preferences[CURRENT_THEME] = themeIndexInThemeList
        }

    }

    val readThemePreference: Flow<Int> = dataStore.data
        .catch {
            if(it is IOException) {
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            preferences[CURRENT_THEME] ?: 4
        }

    suspend fun saveFontPreference(fontIndex: Int) {
        dataStore.edit { preferences ->
            preferences[CURRENT_FONT] = fontIndex
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

    suspend fun saveIntroPreference(introSeen: Boolean) {
        dataStore.edit { preferences ->
            preferences[INTRO_SEEN] = introSeen
        }

    }

    val readIntroPreference: Flow<Boolean> = dataStore.data
        .catch {
            if(it is IOException) {
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            preferences[INTRO_SEEN] ?: false
        }
}