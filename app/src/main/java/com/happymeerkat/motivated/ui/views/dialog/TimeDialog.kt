package com.happymeerkat.motivated.ui.views.dialog

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextStyle
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState
import com.vanpra.composematerialdialogs.datetime.time.TimePickerDefaults
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TimeDialog(
    timeDialogState: MaterialDialogState,
    setNotificationTime: (pickedTime: LocalTime) -> Unit
) {
    var pickedTime by remember{ mutableStateOf(LocalTime.now()) }
    MaterialDialog(
        dialogState = timeDialogState,
        buttons = {
            positiveButton(
                text = "Ok",
               // onClick = {setNotificationTime(pickedTime)},
                onClick = { setNotificationTime(pickedTime) },
                textStyle = TextStyle(color = MaterialTheme.colorScheme.onPrimary)
            )
            negativeButton(
                text = "Cancel",
                textStyle = TextStyle(color = MaterialTheme.colorScheme.onPrimary)
            )
        },
        backgroundColor = MaterialTheme.colorScheme.background
    ) {

        timepicker(
            initialTime = LocalTime.now(),
            title = "Pick a TIME",
            colors = TimePickerDefaults.colors(
                activeBackgroundColor = MaterialTheme.colorScheme.onPrimary,
                inactiveBackgroundColor = MaterialTheme.colorScheme.background,
                activeTextColor = MaterialTheme.colorScheme.background,
                inactiveTextColor = MaterialTheme.colorScheme.onPrimary,
                inactivePeriodBackground = MaterialTheme.colorScheme.background,
                selectorColor = MaterialTheme.colorScheme.onPrimary,
                selectorTextColor = MaterialTheme.colorScheme.onPrimary,
                headerTextColor = MaterialTheme.colorScheme.onPrimary,
                borderColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            pickedTime = it
        }
    }
}