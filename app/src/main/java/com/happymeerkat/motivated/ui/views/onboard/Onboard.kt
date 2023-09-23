package com.happymeerkat.motivated.ui.views.onboard

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.happymeerkat.motivated.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Onboard(
    modifier: Modifier = Modifier
) {
    val pageCount = 3
    val pagerState = rememberPagerState(pageCount = {
        3
    })

    val images = listOf(
        R.drawable.mountain,
        R.drawable.discover,
        R.drawable.bell
    )
    val headlines = listOf(
        "Welcome!",
        "Begin a journey of inspiration",
        "Set reminders"
    )
    val subtext = listOf(
        "Get motivated by the words of other great people like you",
        "Make every day bright with a great quote",
        "We recommend one quote a day but you can change that in settings"
    )

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {

        HorizontalPager(
            modifier = Modifier
                .fillMaxSize()
                .weight(3f),
            state = pagerState
        ) { page ->
            IntroPage(image = images[page], description = "", headline = headlines[page], subtext = subtext[page])
        }
        Row(
            Modifier
                .height(50.dp)
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pageCount) { iteration ->
                val color = if (pagerState.currentPage == iteration) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.background
                val border = if (pagerState.currentPage == iteration)  MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onPrimary
                Box(
                    modifier = Modifier
                        .padding(5.dp)
                        .clip(CircleShape)
                        .background(color)
                        .border(width = 0.3.dp, color = border, shape = CircleShape)
                        .size(10.dp)
                )
            }
            if(pagerState.currentPage == 2) {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Begin your journey")
                }
            }
        }
    }
}