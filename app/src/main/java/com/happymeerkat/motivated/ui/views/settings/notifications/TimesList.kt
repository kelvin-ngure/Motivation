package com.happymeerkat.motivated.ui.views.settings.notifications

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TimesList(
    modifier: Modifier = Modifier,
    times: List<Long> = listOf(1,2,3,4,5,6)
) {
    LazyColumn {
        times.forEach {
            item {
                TimeCard(timeString = it.toString(), deleteTime = {})
            }
        }
    }
}