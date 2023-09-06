package com.happymeerkat.motivated.ui.views.home

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.happymeerkat.motivated.data.models.Quote
import com.happymeerkat.motivated.data.models.Theme
import com.happymeerkat.motivated.ui.views.ImageState

@OptIn(ExperimentalFoundationApi::class, ExperimentalGlideComposeApi::class)
@Composable
fun Home(
    modifier: Modifier = Modifier,
    context: Context,
    quotes: List<Quote>,
    quotePage: Int,
    downloadImage: (awsKey: String) -> Unit,
    imageState: MutableState<ImageState>,
    toggleFavorite: (quote: Quote) -> Unit,
    isFavorite: (quote: Quote) -> Boolean,
    updateQuotePage: (page: Int) -> Unit,
    theme: Theme,
    navigateToSettings: () -> Unit
) {
    val pagerState = rememberPagerState(
        initialPage = quotePage,
        pageCount = {quotes.size}
    )
    val imgState = imageState.value
    var downloaded by remember{ mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxHeight()
            .then(
                if (theme.backgroundColor != null) {
                    Modifier.background(theme.backgroundColor)
                } else {
                    Modifier.background(MaterialTheme.colorScheme.background)
                }

            ),
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
            Image(
                painter = painterResource(id = theme.backgroundImage),
                contentDescription = theme.awsKey,
                contentScale = ContentScale.Crop,
                alpha = 0.3f
            )
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
                context = context,
                quote = quotes[page],
                isFavorite = isFavorite(quotes[page]),
                toggleFavorite = {toggleFavorite(quotes[page])},
                fontId = theme.fontId,
                fontColor = theme.fontColor
            )

        }

        IconButton(
            modifier = Modifier
                .padding(top = 30.dp, end = 15.dp),
            onClick = { navigateToSettings() },
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.background
            )
        }
    }


}

