package com.happymeerkat.motivated.ui.views.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.happymeerkat.motivated.data.models.Quote

@Composable
fun QuoteCard(
    modifier: Modifier,
    showNextQuote: () -> Unit,
    showPreviousQuote: () -> Unit,
    quote: Quote?
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
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = modifier,
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Button to add quote to Favorites"
                    )
                }
                Row(
                    modifier = modifier.padding(top = 22.dp)
                ) {
                    Button(
                        modifier = modifier
                            .weight(1f),
                        shape = (RoundedCornerShape(10.dp)),
                        onClick = { showPreviousQuote() },
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onPrimary)
                    ) {
                        Text(text = "Previous")
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Button(
                        modifier = Modifier
                            .weight(1f),
                        shape = (RoundedCornerShape(10.dp)),
                        onClick = { showNextQuote() },
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onPrimary)
                    ) {
                        Text(text = "Next")
                    }
                }
            }

        }


}