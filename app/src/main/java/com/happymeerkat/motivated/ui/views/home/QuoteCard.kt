package com.happymeerkat.motivated.ui.views.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun QuoteCard(
    modifier: Modifier,
    quote: String,
    author: String? = null,
    context: String? = null
) {
    Card(
        elevation = CardDefaults.cardElevation(15.dp),
        modifier = modifier
    ) {
        Column(
            modifier = modifier.padding(
                start = 22.dp,
                end = 22.dp,
                top = 20.dp,
                bottom = 44.dp
            )
        ) {
            Row(
                modifier = modifier.padding(bottom = 22.dp)
            ) {
                Text(
                    quote,
                    style = MaterialTheme.typography.displayLarge,
                    textAlign = TextAlign.Center
                )
            }
            Row(
                modifier = modifier,
                horizontalArrangement = Arrangement.Center
            ) {
                if (author != null) {
                    Text(
                        author,
                        textAlign = TextAlign.Center
                    )
                }
            }
            Row {
                if (context != null) {
                    Text(context)
                }
            }
        }

    }
}