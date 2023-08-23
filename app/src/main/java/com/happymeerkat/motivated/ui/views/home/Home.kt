package com.happymeerkat.motivated.ui.views.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.happymeerkat.motivated.data.models.Quote

@OptIn(ExperimentalMaterial3Api::class)
enum class DragValue { Start, Center, End }
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Home(
    modifier: Modifier = Modifier,
    quotes: List<Quote>
) {
    val pagerState = rememberPagerState(
        initialPage = 1,
        pageCount = {quotes.size}
    )

    VerticalPager(
        state = pagerState,
        modifier = modifier
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.background)
    ) {page ->
        QuoteCard(
            modifier = Modifier.fillMaxWidth(),
            quote = quotes[page]

        )
    }
}