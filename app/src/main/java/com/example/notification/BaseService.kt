package com.example.notification

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat


class BaseService() : Service() {

    override fun onBind(p0: Intent?): IBinder? {

        return null
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun createNotification(){

        val intentTo = Intent(this, MainActivity2::class.java)

       // intent!!.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK

        val pendingIntent =
            PendingIntent.getActivity(this, 0, intentTo, PendingIntent.FLAG_IMMUTABLE)

        Log.d("suzan", "in notification")

        //val message = intent?.getStringExtra("EXTRA_MESSAGE") ?: return

        val notification = NotificationCompat.Builder(this!!, Constants.channelID)
            .setContentTitle("Notification From Receiver")
            .setContentText("Hello From Receiver")
            .setSmallIcon(R.drawable.ic_baseline_notifications_24)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .build()


        val notificationManager = NotificationManagerCompat.from(this)

        notificationManager.notify(Constants.notificationID, notification)


    }


}
