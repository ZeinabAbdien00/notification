package com.example.notification

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.notification.Constants.requestCode
import java.time.LocalDateTime


class BaseService() : Service() {

    override fun onBind(p0: Intent?): IBinder? {

        return null
    }
    val scheduler = AndroidAlarmScheduler(this)
    var alarmItem: AlarmItem? = null

    override fun onCreate() {
        super.onCreate()

//        createNotification()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        //showNotification()

        Toast.makeText(this , "Hello" , Toast.LENGTH_LONG).show()

//        repaeteNotification()

        return super.onStartCommand(intent, flags, startId)
    }

    private fun repaeteNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            alarmItem = AlarmItem(
                time = LocalDateTime.now().plusSeconds(5),
                message = "Hello World"
            )
            Log.d("suzan", LocalDateTime.now().toString())

        }

        alarmItem?.let(scheduler::schedule)
    }

//    @RequiresApi(Build.VERSION_CODES.M)
//    private fun showNotification() {
//        val intentTo = Intent(this, MainActivity2::class.java)
//
//        val pendingIntent =
//            PendingIntent.getActivity(this, requestCode, intentTo, PendingIntent.FLAG_IMMUTABLE)
//
//        val notification = NotificationCompat.Builder(this!!, Constants.channelID)
//            .setContentTitle("Notification From Receiver")
//            .setContentText("Hello From Receiver")
//            .setSmallIcon(R.drawable.ic_baseline_notifications_24)
//            .setPriority(NotificationCompat.PRIORITY_HIGH)
//            .setContentIntent(pendingIntent)
//            .build()
//
//
//        val notificationManager = NotificationManagerCompat.from(this)
//
//        notificationManager.notify(Constants.notificationID, notification)
//
//        startForeground(1, notification)
//
//    }
//
//    private fun createNotification() {
//
//        val intentTo = Intent(this, MainActivity2::class.java)
//
//        // intent!!.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
//
//        val pendingIntent =
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                PendingIntent.getActivity(this, 0, intentTo, PendingIntent.FLAG_IMMUTABLE)
//            } else {
//                PendingIntent.getActivity(
//                    this,
//                    0,
//                    intentTo,
//                    PendingIntent.FLAG_ONE_SHOT
//                )
//            }
//
//        Log.d("suzan", "in notification")
//
//
//    }



}
