package com.happymeerkat.motivated.ui.views.filters

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Filters(
    modifier: Modifier = Modifier 
) {
    val data = listOf<String>("Hustle", "Stoicism", "Absurdism")
    Column(
        modifier = modifier
            .padding(horizontal = 22.dp, vertical = 11.dp)
    ) {
        LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 128.dp)) {
            items(data) {category ->
                FilterButton(categoryName = category, toggleFilterActive = {})
            }

        }
    }
}