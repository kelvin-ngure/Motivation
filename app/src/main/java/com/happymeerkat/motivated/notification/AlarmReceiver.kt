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
import com.happymeerkat.motivated.notification.AlarmReceiver.SerializableHelper.serializable
import com.happymeerkat.motivated.ui.views.MainActivity
import java.io.Serializable

class AlarmReceiver: BroadcastReceiver() {

    private var notificationManager: NotificationManagerCompat? = null


    override fun onReceive(context: Context?, intent: Intent?) {
        val quote = intent?.serializable("quote") as? Quote

        val markCompletedIntent = Intent(context, MainActivity::class.java)
        markCompletedIntent.flags =Intent.FLAG_ACTIVITY_SINGLE_TOP

        val pendingIntent: PendingIntent = getActivity(context, 0, markCompletedIntent, FLAG_UPDATE_CURRENT or FLAG_IMMUTABLE)

        val intent1 = Intent(context, OnFavoritedBroadCastReceiver::class.java).apply {
            putExtra("quote", quote)
        }
        val pendingIntent1: PendingIntent? = quote?.let { getBroadcast(context, it.id!!, intent1, FLAG_UPDATE_CURRENT or FLAG_IMMUTABLE) }
        val action1 = NotificationCompat.Action.Builder(0, "Completed", pendingIntent1).build()

        val notification = context?.let {
            NotificationCompat.Builder(it, "quote_reminders")
                .setContentTitle(quote?.author)
                .setContentText(quote?.quote)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .addAction(action1)
                .build()
        }

        notificationManager = context?.let { NotificationManagerCompat.from(it) }
        notification?.let { quote?.let { it1 ->
            if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
            notificationManager?.notify(it1.id!!, it); Log.d("ALARM NOTIF", "notification sent") } }
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