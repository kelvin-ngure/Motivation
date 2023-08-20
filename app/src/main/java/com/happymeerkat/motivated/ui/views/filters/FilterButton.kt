package com.happymeerkat.motivated.ui.views.filters

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun FilterButton(
    categoryName: String,
    toggleFilterActive: () -> Unit
) {
    Button(onClick = { toggleFilterActive }) {
        Text(text = categoryName)
    }
}