package com.happymeerkat.motivated.ui.views.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun Home(
    modifier: Modifier = Modifier,
    vm: HomeVM = hiltViewModel()
) {
    val state = vm.homeUIState.collectAsState().value

    LazyColumn(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 22.dp, vertical = 11.dp)
    ) {
        items(state.quotes) {quote ->
            QuoteCard(modifier = Modifier.fillMaxWidth(), quote)
        }
    }
}