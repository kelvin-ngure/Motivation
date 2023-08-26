package com.happymeerkat.motivated.ui.views.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.happymeerkat.motivated.data.models.Quote

@Composable
fun QuoteCard(
    modifier: Modifier,
    quote: Quote,
    toggleFavorite: () -> Unit,
    isFavorite: Boolean
) {
        Column(
            modifier = modifier
                .fillMaxHeight()
        ) {
            // QUOTE DETAILS
            Column(
                modifier = modifier.weight(2f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Row(
                    modifier = modifier
                        .padding(bottom = 22.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    if (quote != null)
                        Text(
                            quote.quote,
                            style = MaterialTheme.typography.displayLarge,
                            textAlign = TextAlign.Center
                        )
                }
                Row(
                    modifier = modifier,
                    horizontalArrangement = Arrangement.Center
                ) {
                    if (quote != null) {
                        if (quote.author != null) {
                            Text(
                                quote.author,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
                Row {
                    if (quote != null) {
                        if (quote.context != null) {
                            Text(quote.context)
                        }
                    }
                }
            }


            // Interactors
            Column(
                modifier = modifier.weight(1f)
            ) {
                Row(
                    modifier = modifier,
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        modifier = Modifier
                            .size(40.dp),
                        onClick = { toggleFavorite() },

                    ) {
                        Icon(
                            imageVector = if(isFavorite) Icons.Filled.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Button to add quote to Favorites"
                        )
                    }
                }
            }

        }


}