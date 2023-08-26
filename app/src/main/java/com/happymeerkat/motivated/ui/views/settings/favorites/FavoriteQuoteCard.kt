package com.happymeerkat.motivated.ui.views.settings.favorites

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.happymeerkat.motivated.data.models.Quote

@Composable
fun FavoriteQuoteCard(
    modifier: Modifier = Modifier,
    quote: Quote,
    toggleFavorite: () -> Unit
) {
  Card(
      modifier = modifier
          .padding(vertical = 10.dp),
      elevation = CardDefaults.cardElevation(defaultElevation = 7.dp),
      colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
      border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.onPrimary)
  ) {
      Row(
          modifier = modifier
              .padding(10.dp)
      ) {
          Text(text = quote.quote)
      }
      if (quote.author != null) {
          Row(
              modifier = modifier
                  .padding(horizontal = 10.dp, vertical = 5.dp)
          ) {
              Text(text = quote.author, fontWeight = FontWeight.SemiBold)
          }
      }
      Row(
          modifier = modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.End
      ) {
          IconButton(onClick = { toggleFavorite() }) {
              Icon(imageVector = Icons.Default.Favorite, contentDescription = "")
          }
      }
  }
}