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
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit


fun getFormattedUTCTime(time: Long): String {
    return Instant.ofEpochMilli(time).atZone(ZoneId.systemDefault().rules.getOffset(Instant.now())).toLocalTime().format(
        DateTimeFormatter.ofPattern("hh:mm a"))
}

fun getFormattedLocalTime(time: LocalTime): String {
    return time.format(DateTimeFormatter.ofPattern("hh:mm a"))
}

fun localDateTimeToEpochMilli(dateTime: LocalDateTime): Long {
    return dateTime
        .toInstant(ZoneId.systemDefault()
            .rules
            .getOffset(Instant.now()))
        .truncatedTo(ChronoUnit.MINUTES)
        .toEpochMilli()
}

fun epochMilliToLocalDateTime(utcTime: Long): LocalDateTime {
    return Instant.ofEpochMilli(utcTime).atZone(ZoneId.systemDefault()).toLocalDateTime();
}

fun alarmSet(
    reminderEpochMilliDateTime: Long,
    context: Context,
    quote: Quote,
    reminder: Reminder, // for automatic alarm reset, we just get the old reminder. for new alarm, reminder is built within this function
    saveReminder: suspend (reminder: Reminder) -> Unit,
) {
    // SAVE REMINDER
    CoroutineScope(Dispatchers.IO).launch {
        saveReminder(reminder)
    }

    // SET ALARM
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val intent = Intent(context, AlarmReceiver::class.java)
    intent.addFlags(Intent.FLAG_RECEIVER_FOREGROUND)
    intent.putExtra("quote", quote)
    intent.putExtra("reminder", reminder)
    val pendingIntent = PendingIntent.getBroadcast(context, reminder.id, intent, PendingIntent.FLAG_IMMUTABLE) // we can guarantee reminder exists since we get the old or new one
    val mainActivityIntent = Intent(context, MainActivity::class.java)
    val basicPendingIntent = PendingIntent.getActivity(context, reminder.id, mainActivityIntent, PendingIntent.FLAG_IMMUTABLE)


    val clockInfo = AlarmManager.AlarmClockInfo(reminderEpochMilliDateTime, basicPendingIntent)
    alarmManager.setAlarmClock(clockInfo, pendingIntent)
}

fun alarmRemove(
    context: Context,
    reminder: Reminder,
    deleteReminder: (suspend (reminder: Reminder) -> Unit)?
) {
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val intent = Intent(context, AlarmReceiver::class.java)
    intent.putExtra("reminder", reminder)
    val pendingIntent = PendingIntent.getBroadcast(context, reminder.id, intent, PendingIntent.FLAG_IMMUTABLE)
    alarmManager.cancel(pendingIntent)
}