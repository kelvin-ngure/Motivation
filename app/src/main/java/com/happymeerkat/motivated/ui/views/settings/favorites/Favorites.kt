package com.happymeerkat.motivated.ui.views.settings.favorites

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Favorites(
    modifier: Modifier = Modifier,
    backToSettings: () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Text("some stuff")
    }
}