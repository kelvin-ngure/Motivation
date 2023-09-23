package com.happymeerkat.motivated.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

private const val THEME_PREFERENCE_NAME = "theme_preference"
val Context.dataStore : DataStore<Preferences> by preferencesDataStore(
    name = THEME_PREFERENCE_NAME
)