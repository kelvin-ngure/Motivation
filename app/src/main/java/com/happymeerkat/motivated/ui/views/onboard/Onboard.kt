package com.happymeerkat.motivated.ui.views.onboard

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.happymeerkat.motivated.R
import com.happymeerkat.motivated.ui.views.dialog.TimeDialog
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Onboard(
    modifier: Modifier = Modifier,
    completeOnboard: () -> Unit
) {
    val timeDialogState = rememberMaterialDialogState()
    var pickedTime by remember{ mutableStateOf<LocalTime?>(null) }
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
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = modifier
                .fillMaxSize()
        ) {

            HorizontalPager(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(6f),
                state = pagerState
            ) { page ->
                IntroPage(
                    page = page,
                    image = images[page],
                    description = "",
                    headline = headlines[page],
                    subtext = subtext[page],
                    completeOnboard = completeOnboard,
                    openClock = {timeDialogState.show()},
                    pickedTime = pickedTime
                )

            }
            Column(
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Row(
                    Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    repeat(pageCount) { iteration ->
                        val color = if (pagerState.currentPage == iteration) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.3f)
                        val size = if (pagerState.currentPage == iteration) 7.dp else 6.dp
                        Box(
                            modifier = Modifier
                                .padding(5.dp)
                                .clip(CircleShape)
                                .background(color)
                                .size(size)
                        )
                    }
                }
            }
        }

        TimeDialog(
            timeDialogState = timeDialogState,
            setNotificationTime = null,
            pickedTime = LocalTime.now(),
            changePickedTime = {it -> pickedTime = it}
        )
    }

}