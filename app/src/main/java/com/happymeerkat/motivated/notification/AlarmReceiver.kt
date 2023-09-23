package com.happymeerkat.motivated.notification
import android.Manifest
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.app.PendingIntent.getActivity
import android.app.PendingIntent.getBroadcast
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.happymeerkat.motivated.R
import com.happymeerkat.motivated.data.models.Quote
import com.happymeerkat.motivated.data.models.Reminder
import com.happymeerkat.motivated.domain.repository.QuoteRepository
import com.happymeerkat.motivated.domain.repository.ReminderRepository
import com.happymeerkat.motivated.notification.AlarmReceiver.SerializableHelper.serializable
import com.happymeerkat.motivated.ui.alarmSet
import com.happymeerkat.motivated.ui.epochMilliToLocalDateTime
import com.happymeerkat.motivated.ui.localDateTimeToEpochMilli
import com.happymeerkat.motivated.ui.views.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.Serializable
import java.time.Instant
import javax.inject.Inject

@AndroidEntryPoint
class AlarmReceiver: BroadcastReceiver() {

    private var notificationManager: NotificationManagerCompat? = null
    @Inject lateinit var quoteRepository: QuoteRepository
    @Inject
    lateinit var reminderRepository: ReminderRepository


    override fun onReceive(context: Context?, intent: Intent?) {
        val quote = intent?.serializable("quote") as? Quote
        val reminder = intent?.serializable("reminder") as? Reminder

        if (quote != null) {
            val quoteNotificationIntent = Intent(context, MainActivity::class.java)
            quoteNotificationIntent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            val quoteNotificationPendingIntent: PendingIntent = getActivity(context, 0, quoteNotificationIntent, FLAG_UPDATE_CURRENT or FLAG_IMMUTABLE)

            val markFavoriteIntent = Intent(context, OnFavoritedBroadCastReceiver::class.java).apply {
                putExtra("quote", quote)
            }
            val markFavoritePendingIntent: PendingIntent? = reminder?.let { getBroadcast(context, reminder.id, markFavoriteIntent, FLAG_UPDATE_CURRENT or FLAG_IMMUTABLE) }
            val markFavoriteAction = NotificationCompat.Action.Builder(0
                ,"Favorite", markFavoritePendingIntent).build()

            val notification = context?.let {
                NotificationCompat.Builder(it, "quote_reminders")
                    .setContentTitle(quote?.author)
                    .setContentText(quote?.quote)
                    .setSmallIcon(R.mipmap.ic_launcher_foreground)
                    .setAutoCancel(true)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setContentIntent(quoteNotificationPendingIntent)
                    .addAction(markFavoriteAction)
                    .build()
            }

            notificationManager = context?.let { NotificationManagerCompat.from(it) }
            notification?.let { reminder!!.let { it1 ->
                if (ActivityCompat.checkSelfPermission(
                        context,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return
                }
                notificationManager?.notify(it1.id, it); Log.d("ALARM NOTIF", "notification sent") } }
        } else{
            Log.d("ALARM", "quote received :none")
        }


        // SET THE NEXT RECURRING ALARM
        val epochMilli = reminder?.time
        val reminderDateTime = epochMilli?.let { epochMilliToLocalDateTime(it) }
        val nextReminderDateTime = reminderDateTime?.plusHours(24)
        val nextReminderEpochMilli = nextReminderDateTime?.let { localDateTimeToEpochMilli(it) }
        val newReminder = nextReminderEpochMilli?.let {
            Reminder(
                id = Instant.now().toEpochMilli().toInt(),
                time = it
            )
        }

        // delete the old reminder because we need a new reminder id every time or else every reminder will have the same requestCode , leading to it being the same quote forever
        CoroutineScope(Dispatchers.IO).launch{
            if (reminder != null) {
                reminderRepository.deleteReminder(reminder)
            }
        }

        CoroutineScope(Dispatchers.Unconfined).launch {
            Log.d("ALARM", "COROUTINE")
            val randomQuote = quoteRepository.getRandomQuote()
            Log.d("ALARM", "quote sent coroutine ${randomQuote.quote}")
            if (nextReminderEpochMilli != null) {
                alarmSet(
                    reminderEpochMilliDateTime = nextReminderEpochMilli,
                    context = context!!,
                    quote = randomQuote,
                    saveReminder = {reminder -> reminderRepository.insertReminder(reminder)},
                    reminder = newReminder!!
                )
            }
        }
    }

    object SerializableHelper {
        inline fun <reified T : Serializable> Bundle.serializable(key: String): T? = when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> getSerializable(key, T::class.java)
            else -> @Suppress("DEPRECATION") getSerializable(key) as? T
        }

        inline fun <reified T : Serializable> Intent.serializable(key: String): T? = when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> getSerializableExtra(key, T::class.java)
            else -> @Suppress("DEPRECATION") getSerializableExtra(key) as? T
        }
    }

}