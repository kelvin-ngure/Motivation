package com.happymeerkat.motivated.ui.views.settings.fonts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.happymeerkat.motivated.ui.views.util.AppBar
import com.happymeerkat.motivated.ui.views.vm.FontMenuVM

@Composable
fun Fonts(
    modifier: Modifier = Modifier.fillMaxSize(),
    backToSettings: () -> Unit,
    vm: FontMenuVM = hiltViewModel()
) {
    val state = vm.fontMenuUI.collectAsState().value

    Scaffold(
        topBar = { AppBar(
            title = "Fonts",
            goBack = {backToSettings()}
        )
        }
    ) { it ->
        LazyVerticalGrid(
            modifier = modifier
                .padding(it)
                .background(MaterialTheme.colorScheme.background),
            columns = GridCells.Fixed(3)
        ) {
            items(state.fonts) { fontId ->
                FontBox(
                    fontId = fontId,
                    changeFont = {vm.changeSelectedFont(fontId =  fontId)},
                    isCurrentFont = state.selectedFont == fontId
                )
            }
        }
    }
}
