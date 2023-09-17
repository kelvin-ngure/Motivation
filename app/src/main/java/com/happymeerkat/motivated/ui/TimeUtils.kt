package com.happymeerkat.motivated.ui

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import com.happymeerkat.motivated.data.models.Quote
import com.happymeerkat.motivated.data.models.Reminder
import com.happymeerkat.motivated.notification.AlarmReceiver
import com.happymeerkat.motivated.ui.views.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun alarmSet(
    timeChosen: LocalTime,
    context: Context,
    quote: Quote,
    saveReminder: (reminder: Reminder) -> Unit
) {
    // if past time, set alarm for tomorrow
    val today = LocalDate.now()
    val tomorrow = today.plusDays(1)
    val alarmDateTime: LocalDateTime = if (timeChosen >= LocalTime.now()) timeChosen.atDate(today) else timeChosen.atDate(tomorrow)

    // store UTC Long in DB
    val alarmDateTimeMilliseconds = alarmDateTime.toInstant(
        ZoneId.systemDefault().rules.getOffset(
            Instant.now())).toEpochMilli()
    Log.d("ALARM", "alarm ms $alarmDateTimeMilliseconds")

    val reminder = Reminder(
        id = 1,
        time = alarmDateTimeMilliseconds
    )

    CoroutineScope(Dispatchers.IO).launch {
        saveReminder(reminder)
    }

    // Alarm and intents
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val intent = Intent(context, AlarmReceiver::class.java)
    intent.addFlags(Intent.FLAG_RECEIVER_FOREGROUND)
    intent.putExtra("quote", quote)
    val pendingIntent = PendingIntent.getBroadcast(context, quote.id, intent, PendingIntent.FLAG_IMMUTABLE)
    val mainActivityIntent = Intent(context, MainActivity::class.java)
    val basicPendingIntent = PendingIntent.getActivity(context, quote.id, mainActivityIntent, PendingIntent.FLAG_IMMUTABLE)


    val clockInfo = AlarmManager.AlarmClockInfo(alarmDateTimeMilliseconds, basicPendingIntent)
    alarmManager.setAlarmClock(clockInfo, pendingIntent)
}

fun alarmRemove() {

}