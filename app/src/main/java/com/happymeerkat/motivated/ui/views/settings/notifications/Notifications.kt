package com.happymeerkat.motivated.ui.views.settings.notifications

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.happymeerkat.motivated.ui.views.dialog.TimeDialog
import com.happymeerkat.motivated.ui.views.util.AppBar
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Notifications(
    modifier: Modifier = Modifier,
    backToSettings: () -> Unit,
    setNotificationTime: (pickedTime: LocalTime) -> Unit
) {
    val timeDialogState = rememberMaterialDialogState()

    Scaffold(
        modifier = modifier,
        topBar = { AppBar(
            title = "Notifications",
            goBack = {backToSettings()}
        )
        }
    ) { it ->
        Box(
            modifier = modifier
                .padding(it)
        ) {
            Column(
                modifier = Modifier.padding(start = 22.dp, end = 22.dp)
            ) {
                AddNewCard(
                    addNewTime = {},
                    openTimeDialog = {timeDialogState.show()},
                )
                TimesList()
                TimeDialog(
                    timeDialogState = timeDialogState,
                    setNotificationTime = setNotificationTime
                )
            }
        }
    }
}