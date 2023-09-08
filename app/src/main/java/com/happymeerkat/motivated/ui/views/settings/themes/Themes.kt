package com.happymeerkat.motivated.ui.views.settings.themes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.happymeerkat.motivated.data.models.Theme
import com.happymeerkat.motivated.domain.themes.ThemeType
import com.happymeerkat.motivated.ui.views.util.AppBar

@Composable
fun Themes(
    modifier: Modifier = Modifier.fillMaxSize(),
    backToSettings: () -> Unit,
    themes: List<Theme>,
    currentTheme: Theme,
    changeCurrentTheme: (theme: Theme) -> Unit
) {

    Scaffold(
        modifier = modifier,
        topBar = { AppBar(
            title = "Themes",
            goBack = {backToSettings()}
        )
        }
    ) { it ->
        Column(
            modifier = modifier
                .padding(it)
        ) {
            // themetype, index
            ThemeType.values().forEach { themeType ->
                ThemeSelection(
                    themeTypeName = themeType.toString(),
                    themes = themes.filter { it.themeType == themeType },
                    changeCurrentTheme = {theme -> changeCurrentTheme(theme)},
                    currentTheme = currentTheme!!
                )

            }
//            LazyVerticalGrid(
//                modifier = Modifier
//                    .padding(it),
//                columns = GridCells.Fixed(3)
//            ) {
//                items(state.themes) { theme ->
//                    ThemeBox(
//                        theme = theme,
//                        changeTheme = {vm.changeCurrentTheme(theme = theme)},
//                        isCurrentTheme = state.selectedTheme == theme
//                    )
//                }
//            }
        }
    }
}

@Composable
fun ThemeSelection(
    modifier: Modifier = Modifier.fillMaxWidth(),
    themeTypeName: String,
    themes: List<Theme>,
    changeCurrentTheme: (theme: Theme) -> Unit,
    currentTheme: Theme
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(modifier = Modifier.padding(start = 10.dp), text = themeTypeName)
            //Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "more themes")
        }

        Column {
            LazyRow {
                items(
                    themes,
                    key = {it.themeId}
                ) { theme ->
                    ThemeBox(
                        theme = theme,
                        changeTheme = {changeCurrentTheme(theme)},
                        isCurrentTheme = currentTheme == theme
                    )
                }
            }
        }

    }
}
