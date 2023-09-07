package com.happymeerkat.motivated.ui.views.settings.themes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.happymeerkat.motivated.data.models.Theme
import com.happymeerkat.motivated.domain.themes.ThemeType
import com.happymeerkat.motivated.ui.views.util.AppBar
import com.happymeerkat.motivated.ui.views.vm.ThemeMenuVM

@Composable
fun Themes(
    modifier: Modifier = Modifier.fillMaxSize(),
    backToSettings: () -> Unit,
    themeGroups: HashMap<ThemeType, List<Int>>,
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
            modifier = modifier
                .padding(it)
        ) {
            // themetype, index
            themeGroups.forEach { themeGroup ->
                ThemeSelection(
                    groupName = themeGroup.key.toString(),
                    indices = themeGroup.value,
                    themes = state.themes,
                    changeSelectedTheme = {theme -> vm.changeSelectedTheme(theme)},
                    selectedTheme = state.selectedTheme!!
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
//                        changeTheme = {vm.changeSelectedTheme(theme = theme)},
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
    groupName: String,
    indices: List<Int>,
    themes: List<Theme>,
    changeSelectedTheme: (theme: Theme) -> Unit,
    selectedTheme: Theme
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(modifier = Modifier.padding(start = 10.dp), text = groupName)
            //Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "more themes")
        }

        Column {
            LazyRow {
                items(indices) { index ->
                    ThemeBox(
                        theme = themes[index],
                        changeTheme = {changeSelectedTheme(themes[index])},
                        isCurrentTheme = selectedTheme == themes[index]
                    )
                }
            }
        }

    }
}
