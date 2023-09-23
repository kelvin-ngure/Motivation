package com.happymeerkat.motivated.notification;

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.happymeerkat.motivated.domain.repository.QuoteRepository
import com.happymeerkat.motivated.domain.repository.ReminderRepository
import com.happymeerkat.motivated.ui.alarmSet
import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId

@AndroidEntryPoint
class RebootBroadcastReceiver: BroadcastReceiver() {

    @Inject
    lateinit var reminderRepository: ReminderRepository
    @Inject
    lateinit var quoteRepository: QuoteRepository

    override fun onReceive(context: Context?, intent: Intent?) {
        val time = LocalTime.now().toEpochSecond(
            LocalDate.now(),
            ZoneId.systemDefault().rules.getOffset(Instant.now())
        )

        CoroutineScope(Dispatchers.IO).launch {
            val activeReminders = reminderRepository.getActiveReminders(time)
            Log.d("ALARM PENDING", activeReminders.size.toString())
            activeReminders.forEach { reminder ->
                CoroutineScope(Dispatchers.IO).launch {
                    Log.d("ALARM", "COROUTINE")
                    val randomQuote = quoteRepository.getRandomQuote()
                    alarmSet(
                        reminderEpochMilliDateTime = reminder.time,
                        context = context!!,
                        quote = randomQuote,
                        reminder = reminder,
                        saveReminder = {reminder -> reminderRepository.insertReminder(reminder)}
                    )
                }
            }
        }
    }
}


