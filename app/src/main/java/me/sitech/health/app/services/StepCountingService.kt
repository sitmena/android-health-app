package me.sitech.health.app.services

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import me.sitech.health.R
import me.sitech.health.presentation.activity.MainActivity

class StepCountingService : Service() {

    private val ONGOING_NOTIFICATION_ID = 1001
    private val CHANNEL_ID = 1002


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        // If the notification supports a direct reply action, use
        // PendingIntent.FLAG_MUTABLE instead.


        createOrUpdateNotification(true, "Hello", "Test")

        Handler(Looper.getMainLooper()).postDelayed({
            createOrUpdateNotification(false, "Title Updated", "Message updated")

        }, 5000)
        return super.onStartCommand(intent, flags, startId)
    }

    private fun createOrUpdateNotification(isUpdate: Boolean, title: String, text: String) {

        if (!isUpdate) {
            createNotificationChannel()
        }


        startForeground(ONGOING_NOTIFICATION_ID, getNotification(title, text))
    }

    private fun getNotification(title: String, text: String): Notification {

        val pendingIntent: PendingIntent =
            Intent(this, MainActivity::class.java).let { notificationIntent ->
                PendingIntent.getActivity(
                    this, 0, notificationIntent,
                    PendingIntent.FLAG_IMMUTABLE
                )
            }

        return Notification.Builder(this, CHANNEL_ID.toString())
            .setContentTitle(title)
            .setContentText(text)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .build()


    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        val name = "Test channel"
        val descriptionText = "Channel desc"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_ID.toString(), name, importance).apply {
            description = descriptionText
        }
        // Register the channel with the system
        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    override fun onBind(intent: Intent): IBinder? = null
}