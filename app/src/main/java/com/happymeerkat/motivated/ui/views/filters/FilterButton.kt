package com.happymeerkat.motivated.ui.views.filters

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun FilterButton(
    modifier: Modifier = Modifier,
    categoryName: String,
    toggleFilterActive: () -> Unit,
) {
    Button(
        modifier = modifier,
        onClick = { toggleFilterActive }
    ) {
        Text(text = categoryName)
    }
}