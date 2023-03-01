package com.example.notification

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import java.util.*


class Notification : BroadcastReceiver() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onReceive(context: Context?, intent: Intent?) {

        val intentTo = Intent(context, MainActivity2::class.java)

        intent!!.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK

        val pendingIntent =
            PendingIntent.getActivity(context, resultCode, intentTo, PendingIntent.FLAG_IMMUTABLE)

        Log.d("suzan", "in notification")

        //val message = intent?.getStringExtra("EXTRA_MESSAGE") ?: return

        val notification = NotificationCompat.Builder(context!!, Constants.channelID)
            .setContentTitle("Notification From Receiver")
            .setContentText("Hello From Receiver")
            .setSmallIcon(R.drawable.ic_baseline_notifications_24)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .build()


        val notificationManager = NotificationManagerCompat.from(context)

        notificationManager.notify(Constants.notificationID, notification)

        if (intent.action == "android.intent.action.BOOT_COMPLETED") {
            val calendar = Calendar.getInstance()
            val alarmUtils = AlarmUtils(context)
            alarmUtils.initRepeatingAlarm(calendar)

            //println("Alarm triggered: $message")
        }
    }
}