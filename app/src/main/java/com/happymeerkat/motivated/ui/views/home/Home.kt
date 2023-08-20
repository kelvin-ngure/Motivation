package com.happymeerkat.motivated.ui.views.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.happymeerkat.motivated.data.models.Quote

@Composable
fun Home(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.background(MaterialTheme.colorScheme.background).padding(horizontal = 22.dp, vertical = 11.dp)
    ) {
        QuoteCard(modifier = Modifier.fillMaxWidth(), Quote(1, "And I cannot sell my soul", author = "Roddy Rich", categoryId = 1))
        QuoteCard(modifier = Modifier.fillMaxWidth(), Quote(1, "And I cannot sell my soul", author = "Roddy Rich", categoryId = 1))
    }
}