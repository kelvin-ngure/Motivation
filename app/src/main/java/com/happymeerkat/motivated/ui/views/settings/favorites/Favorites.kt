package com.happymeerkat.motivated.ui.views.settings.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.happymeerkat.motivated.data.models.Quote
import com.happymeerkat.motivated.ui.views.util.AppBar

@Composable
fun Favorites(
    modifier: Modifier = Modifier,
    backToSettings: () -> Unit,
    favoriteQuotes: List<Quote>,
    toggleFavorite: (quote: Quote) -> Unit
) {
    Scaffold(
        topBar = { AppBar(title = "Favorites")}
    ) { it ->
        Column(
            modifier = modifier.padding(it)
        ) {

            if (favoriteQuotes.isEmpty()) {
                Column(
                    modifier = modifier
                        .fillMaxHeight()
                        .padding(horizontal = 22.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        textAlign = TextAlign.Center,
                        text = "Favorite some quotes to see them here!"
                    )
                }
            } else {
                LazyColumn(
                    modifier = modifier.padding(horizontal = 22.dp)
                ) {
                    items(favoriteQuotes) {
                        FavoriteQuoteCard(
                            modifier = modifier,
                            quote = it,
                            toggleFavorite = {toggleFavorite(it)}
                        )
                    }
                }
            }

        }
    }

}

