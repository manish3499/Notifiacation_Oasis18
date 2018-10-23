package org.bits_oasis.notification_oasis18

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import android.app.AlarmManager
import android.os.SystemClock
import android.app.PendingIntent
import android.content.Intent
import android.media.RingtoneManager
import android.graphics.drawable.BitmapDrawable
import android.icu.text.Normalizer.NO
import android.view.View


class MainActivity : AppCompatActivity() {

    private val CHANNEL_ID = "ID"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        createNotificationChannel()


        var notification = Notification()
        notification.instantNotification(this,1,"test1","small text", "big text")
        notification.instantNotification(this,2,"test2","small text")
        notification.scheduleNotification(this, 1000, 3, "test3", "small text", "big text")
        notification.scheduleNotification(this, 2000, 4, "test4", "small text")





    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }



}
