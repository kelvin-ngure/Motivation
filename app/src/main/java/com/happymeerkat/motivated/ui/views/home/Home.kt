package com.happymeerkat.motivated.ui.views.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.happymeerkat.motivated.data.models.Quote

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Home(
    modifier: Modifier = Modifier,
    quotes: List<Quote>,
    quotePage: Int,
    toggleFavorite: (quote: Quote) -> Unit,
    isFavorite: (quote: Quote) -> Boolean,
    updateQuotePage: (page: Int) -> Unit
) {
    val pagerState = rememberPagerState(
        initialPage = quotePage,
        pageCount = {quotes.size}
    )

    VerticalPager(
        state = pagerState,
        modifier = modifier
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.background)
    ) {page ->
        updateQuotePage(page)
        QuoteCard(
            modifier = Modifier.fillMaxWidth(),
            quote = quotes[page],
            isFavorite = isFavorite(quotes[page]),
            toggleFavorite = {toggleFavorite(quotes[page])}
        )

    }
}