package com.happymeerkat.motivated.ui.views.settings.notifications

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.happymeerkat.motivated.ui.views.util.AppBar

@Composable
fun Notifications(
    modifier: Modifier = Modifier,
    backToSettings: () -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = { AppBar(
            title = "Notifications",
            goBack = {backToSettings()}
        )
        }
    ) { it ->
        Column(
            modifier = modifier
                .padding(it)
        ) {
            Column(
                modifier = Modifier.padding(start = 22.dp, end = 22.dp)
            ) {
                AddNewCard(
                    addNewTime = {}
                )
                TimesList()
            }

        }
    }
}