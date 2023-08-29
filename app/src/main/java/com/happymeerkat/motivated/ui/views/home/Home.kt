package com.happymeerkat.motivated.ui.views.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.happymeerkat.motivated.data.models.Quote

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Home(
    modifier: Modifier = Modifier,
    quotes: List<Quote>,
    quotePage: Int,
    toggleFavorite: (quote: Quote) -> Unit,
    isFavorite: (quote: Quote) -> Boolean,
    updateQuotePage: (page: Int) -> Unit,
    fontId: Int,
    background: Color?,
    navigateToSettings: () -> Unit
) {
    val pagerState = rememberPagerState(
        initialPage = quotePage,
        pageCount = {quotes.size}
    )
    Box(
        modifier = modifier
            .fillMaxHeight(),
        contentAlignment = Alignment.BottomEnd
    ) {
        VerticalPager(
            state = pagerState,
            modifier = modifier
                .fillMaxHeight()
                .background(background ?: MaterialTheme.colorScheme.background)
        ) {page ->
            updateQuotePage(page)
            QuoteCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp),
                quote = quotes[page],
                isFavorite = isFavorite(quotes[page]),
                toggleFavorite = {toggleFavorite(quotes[page])},
                fontId = fontId
            )

        }

        FloatingActionButton(
            modifier = Modifier.padding(bottom = 60.dp, end = 22.dp),
            onClick = { navigateToSettings() }
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "")
        }
    }


}