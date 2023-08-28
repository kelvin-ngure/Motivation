package com.happymeerkat.motivated.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

private const val FONT_PREFERENCE_NAME = "font_preference"
val Context.dataStore : DataStore<Preferences> by preferencesDataStore(
    name = FONT_PREFERENCE_NAME
)