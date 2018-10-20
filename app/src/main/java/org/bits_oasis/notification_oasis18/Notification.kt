package org.bits_oasis.notification_oasis18

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat

class Notification{

    private val CHANNEL_ID = "ID"


    fun set(context: Context,notificationId: Int, title: String, smallText: String, bigText: String, importance: Int) {
        //initialize the notification
        var mBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.notification_icon_background)
            .setContentTitle(title)
            .setContentText(smallText)
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText(bigText))
            .setDefaults(Notification.DEFAULT_ALL)
            .setPriority(importance)

        //launch the notification
        with(NotificationManagerCompat.from(context)) {
            // notificationId is a unique int for each notification that you must define
            notify(notificationId, mBuilder.build())
        }
    }

    fun cancel(context: Context, notificationId: Int) {
        with(NotificationManagerCompat.from(context)) {
            // notificationId is a unique int for each notification that you must define
            cancel(notificationId)
        }
    }

    fun cancelAll(context: Context) {
        with(NotificationManagerCompat.from(context)) {
            cancelAll()
        }
    }


}