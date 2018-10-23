package org.bits_oasis.notification_oasis18

import android.app.AlarmManager
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.SystemClock
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat

class Notification{

    private val CHANNEL_ID = "ID"


    /** Function to create an instant notification for large notification
     *
     */
    fun instantNotification(context: Context,notificationId: Int, title: String, smallText: String, bigText: String) {
        //initialize the notification
        var mBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.notification_icon_background)
            .setContentTitle(title)
            .setContentText(smallText)
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText(bigText))
            .setDefaults(Notification.DEFAULT_ALL)
            .setPriority(NotificationManager.IMPORTANCE_HIGH)

        //launch the notification
        with(NotificationManagerCompat.from(context)) {
            // notificationId is a unique int for each notification that you must define
            notify(notificationId, mBuilder.build())
        }
    }

    /** Function to create instant notification for small notification
     *
     */
    fun instantNotification(context: Context,notificationId: Int, title: String, smallText: String) {
        //initialize the notification
        var mBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.notification_icon_background)
            .setContentTitle(title)
            .setContentText(smallText)
            .setDefaults(Notification.DEFAULT_ALL)
            .setPriority(NotificationManager.IMPORTANCE_HIGH)

        //launch the notification
        with(NotificationManagerCompat.from(context)) {
            // notificationId is a unique int for each notification that you must define
            notify(notificationId, mBuilder.build())
        }
    }

    /** Function to schedule large notifications
     *
     */
    fun scheduleNotification(
        context: Context,
        delay: Long,
        notificationId: Int,
        title: String,
        smallText: String,
        bigText: String
    ) {//delay is after how much time(in millis) from current time you want to schedule the notification
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(smallText)
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText(bigText))
            .setAutoCancel(true)
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .setSmallIcon(R.drawable.notification_icon_background)
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))

        val intent = Intent(context, MainActivity::class.java)
        val activity = PendingIntent.getActivity(context, notificationId, intent, PendingIntent.FLAG_CANCEL_CURRENT)
        builder.setContentIntent(activity)

        val notification = builder.build()

        val notificationIntent = Intent(context, MyNotificationPublisher::class.java)
        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION_ID, notificationId)
        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION, notification)
        val pendingIntent =
            PendingIntent.getBroadcast(context, notificationId, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT)

        val futureInMillis = SystemClock.elapsedRealtime() + delay
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent)
    }

    /** Function to schedule small notifications
     *
     */
    fun scheduleNotification(
        context: Context,
        delay: Long,
        notificationId: Int,
        title: String,
        smallText: String
    ) {//delay is after how much time(in millis) from current time you want to schedule the notification
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(smallText)
            .setAutoCancel(true)
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .setSmallIcon(R.drawable.notification_icon_background)
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))

        val intent = Intent(context, MainActivity::class.java)
        val activity = PendingIntent.getActivity(context, notificationId, intent, PendingIntent.FLAG_CANCEL_CURRENT)
        builder.setContentIntent(activity)

        val notification = builder.build()

        val notificationIntent = Intent(context, MyNotificationPublisher::class.java)
        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION_ID, notificationId)
        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION, notification)
        val pendingIntent =
            PendingIntent.getBroadcast(context, notificationId, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT)

        val futureInMillis = SystemClock.elapsedRealtime() + delay
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent)
    }


    /** Function to cancel a specific notification sent from the app
     *
     */
    fun cancel(context: Context, notificationId: Int) {
        with(NotificationManagerCompat.from(context)) {
            // notificationId is a unique int for each notification that you must define
            cancel(notificationId)
        }
    }

    /** Function to cancel all notifications sent from the app
     *
     */
    fun cancelAll(context: Context) {
        with(NotificationManagerCompat.from(context)) {
            cancelAll()
        }
    }


}