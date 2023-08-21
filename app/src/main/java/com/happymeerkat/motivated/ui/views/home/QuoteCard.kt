package com.happymeerkat.motivated.ui.views.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.happymeerkat.motivated.data.models.Quote

@Composable
fun QuoteCard(
    modifier: Modifier,
    quote: Quote
) {
    Card(
        elevation = CardDefaults.cardElevation(15.dp),
        modifier = modifier.padding(vertical = 18.dp)
    ) {
        Column(
            modifier = modifier.padding(
                start = 22.dp,
                end = 22.dp,
                top = 20.dp,
                bottom = 22.dp
            )
        ) {
            Row(
                modifier = modifier.padding(bottom = 22.dp)
            ) {
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
                if (quote.author != null) {
                    Text(
                        quote.author,
                        textAlign = TextAlign.Center
                    )
                }
            }
            Row {
                if (quote.context != null) {
                    Text(quote.context)
                }
            }
            Row(
                modifier = modifier.padding(top = 22.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Icon(imageVector = Icons.Default.Favorite, contentDescription = "Button to add quote to Favorites")
            }
        }

    }
}