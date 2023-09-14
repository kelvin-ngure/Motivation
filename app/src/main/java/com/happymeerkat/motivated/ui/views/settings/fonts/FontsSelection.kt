package com.happymeerkat.motivated.ui.views.settings.fonts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.happymeerkat.motivated.ui.views.util.AppBar

@Composable

fun FontsSelection(
    modifier: Modifier = Modifier.fillMaxSize(),
    backToSettings: () -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = { AppBar(
            title = "Fonts",
            goBack = {backToSettings()}
        )
        }
    ) { it ->
        Column(
            modifier = Modifier.padding(it)
        ) {

        }
    }
}