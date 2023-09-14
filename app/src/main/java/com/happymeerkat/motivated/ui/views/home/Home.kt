package com.happymeerkat.motivated.ui.views.home

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.happymeerkat.motivated.data.models.Quote
import com.happymeerkat.motivated.data.models.Theme

@OptIn(ExperimentalFoundationApi::class, ExperimentalGlideComposeApi::class)
@Composable
fun Home(
    modifier: Modifier = Modifier,
    context: Context,
    quotes: List<Quote>,
    quotePage: Int,
    toggleFavorite: (quote: Quote) -> Unit,
    isFavorite: (quote: Quote) -> Boolean,
    updateQuotePage: (page: Int) -> Unit,
    theme: Theme,
    navigateToSettings: () -> Unit,
    notificationPermission: () -> Unit
) {
    val pagerState = rememberPagerState(
        initialPage = quotePage,
        pageCount = {quotes.size}
    )
//    val imgState = imageState.value
//    var downloaded by remember{ mutableStateOf(false) }
    var hidden by remember{ mutableIntStateOf(0) }
    notificationPermission()
    Box(
        modifier = modifier
            .fillMaxHeight(),
        contentAlignment = Alignment.TopEnd
    ) {

        // TODO: DO NOT DELETE WILL WORK ON AWS PULLING LATER
//        if(theme.awsKey != null) {
//            if(downloaded) {
//                when(imgState) {
//                    is ImageState.ImageDownloaded -> {
//                        Log.d("AMPLIFY", "downloaded image ${imgState.downloadedImageFile}")
//                        GlideImage(
//                            model = imgState.downloadedImageFile,
//                            contentDescription = "sunset tree",
//                            contentScale = ContentScale.Crop
//                        )
//                    }
//
//                    else -> {}
//                }
//            } else{
//                Log.d("AMPLIFY", "recompose")
//                downloadImage(theme.awsKey)
//                downloaded = true
//            }
//        }
        if(theme.backgroundImage != null) {
            GlideImage(
                model = theme.backgroundImage,
                contentDescription = theme.awsKey,
                contentScale = ContentScale.Crop,
            )
        }
        if( theme.backgroundColor != null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(theme.backgroundColor!!)
            ) {

            }
        }

        VerticalPager(
            state = pagerState,
            modifier = modifier
                .fillMaxHeight(),
            beyondBoundsPageCount = 5
        ) {page ->
            updateQuotePage(page)
            QuoteCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp),
                context = context,
                quote = quotes[page],
                isFavorite = isFavorite(quotes[page]),
                toggleFavorite = {toggleFavorite(quotes[page])},
                fontId = theme.fontId,
                fontColor = theme.fontColor,
                hidden = hidden,
                toggleHidden = {hidden = (hidden+1)%2}
            )

        }
        Column(
            modifier = modifier.statusBarsPadding()
        ) {
            if(hidden == 0) {
                IconButton(
                    modifier = Modifier.padding(end = 14.dp, top = 5.dp),
                    onClick = { navigateToSettings() },
                ) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "",
                        tint = theme.fontColor ?: MaterialTheme.colorScheme.onPrimary
                    )
                }
            }

        }


    }


}

