package org.bits_oasis.notification_oasis18

import android.app.Notification
import android.content.Context.NOTIFICATION_SERVICE
import android.support.v4.content.ContextCompat.getSystemService
import android.app.NotificationManager
import android.content.Intent
import android.content.BroadcastReceiver
import android.content.Context
import android.os.Parcelable


class MyNotificationPublisher : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        var notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        var notification = intent.getParcelableExtra<Notification>(NOTIFICATION)
        var notificationId = intent.getIntExtra(NOTIFICATION_ID, 0)
        notificationManager.notify(notificationId, notification)
    }

    companion object {

        var NOTIFICATION_ID = "notification_id"
        var NOTIFICATION = "notification"
    }
}