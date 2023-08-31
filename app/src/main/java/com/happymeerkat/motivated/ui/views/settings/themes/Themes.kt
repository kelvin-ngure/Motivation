package com.happymeerkat.motivated.ui.views.settings.themes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.happymeerkat.motivated.ui.views.util.AppBar
import com.happymeerkat.motivated.ui.views.vm.ThemeMenuVM

@Composable
fun Themes(
    modifier: Modifier = Modifier.fillMaxSize(),
    backToSettings: () -> Unit,
    vm: ThemeMenuVM = hiltViewModel()
) {
    val state = vm.themeMenuUI.collectAsState().value

    Scaffold(
        modifier = modifier,
        topBar = { AppBar(
            title = "Themes",
            goBack = {backToSettings()}
        )
        }
    ) { it ->
        Column(
            modifier = Modifier
                .fillMaxHeight()
        ) {
            LazyVerticalGrid(
                modifier = Modifier
                    .padding(it),
                columns = GridCells.Fixed(3)
            ) {
                items(state.themes) { theme ->
                    ThemeBox(
                        theme = theme,
                        changeTheme = {vm.changeSelectedTheme(theme = theme)},
                        isCurrentTheme = state.selectedTheme == theme
                    )
                }
            }
        }
    }
}
