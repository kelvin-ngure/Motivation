package com.happymeerkat.motivated.ui.views.settings.notifications

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.happymeerkat.motivated.data.models.Reminder

@Composable
fun TimesList(
    modifier: Modifier = Modifier,
    reminders: List<Reminder>,
    deleteReminder: (reminder: Reminder) -> Unit
) {
    LazyColumn {
        reminders.forEach {
            item {
                TimeCard(
                    reminder = it,
                    deleteReminder = {deleteReminder(it)}
                )
            }
        }
    }
}