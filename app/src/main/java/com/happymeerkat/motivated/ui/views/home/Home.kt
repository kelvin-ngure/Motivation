package com.happymeerkat.motivated.ui.views.home

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.TextDelegate.Companion.paint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.happymeerkat.motivated.data.models.Quote
import com.happymeerkat.motivated.data.models.Theme

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
    theme: Theme,
    navigateToSettings: () -> Unit
) {
    Log.d("THEME", theme.toString())
    val pagerState = rememberPagerState(
        initialPage = quotePage,
        pageCount = {quotes.size}
    )
    Box(
        modifier = modifier
            .fillMaxHeight()
            .then(
                if (theme.backgroundImage != null) {
                    Modifier.paint(
                        painterResource(id = theme.backgroundImage),
                        contentScale = ContentScale.FillBounds,
                        alpha = 1f
                    )
                } else {
                    if (theme.backgroundColor != null) {
                        Modifier.background(theme.backgroundColor)
                    } else {
                        Modifier.background(MaterialTheme.colorScheme.background)
                    }
                }
            ),
        contentAlignment = Alignment.TopEnd
    ) {
        Column(
            modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.6f))
        ) {

        }
        VerticalPager(
            state = pagerState,
            modifier = modifier
                .fillMaxHeight()
        ) {page ->
            updateQuotePage(page)
            QuoteCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp),
                quote = quotes[page],
                isFavorite = isFavorite(quotes[page]),
                toggleFavorite = {toggleFavorite(quotes[page])},
                fontId = fontId,
                fontColor = theme.fontColor
            )

        }

        IconButton(
            modifier = Modifier
                .padding(top = 30.dp, end = 15.dp)
                .clip(RoundedCornerShape(5.dp, 5.dp, 5.dp, 5.dp)),
            onClick = { navigateToSettings() },
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = Color.White
            )
        ) {
            Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "")
        }
    }


}