package com.example.notification

import android.R
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.notification.Constants.channelID
import com.example.notification.Constants.notificationID
import java.util.*


class Notification : BroadcastReceiver() {



    @RequiresApi(Build.VERSION_CODES.M)
    override fun onReceive(context: Context?, intent: Intent?) {


        val `when` = System.currentTimeMillis()
        val notificationManager = context!!
            .getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notificationIntent = Intent(context, MainActivity2::class.java)
        notificationIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP

        val pendingIntent = PendingIntent.getActivity(context, 0,
            notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT)


        val alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val mNotifyBuilder: NotificationCompat.Builder = NotificationCompat.Builder(
            context).setSmallIcon(R.drawable.ic_notification_overlay)
            .setContentTitle("Alarm Fired")
            .setContentText("Events to be Performed").setSound(alarmSound)
            .setAutoCancel(true).setWhen(`when`)
            .setContentIntent(pendingIntent)
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
        notificationManager.notify(notificationID, mNotifyBuilder.build())
        notificationID++


    }
}


//        val intentTo = Intent(context, BaseService::class.java)

//
//
//        //intent!!.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
//
//        val pendingIntent =
//            PendingIntent.getActivity(context,
//                resultCode,
//                intentTo,
//                PendingIntent.FLAG_IMMUTABLE)
//
//        Log.d("suzan", "in notification")
//
//        //val message = intent?.getStringExtra("EXTRA_MESSAGE") ?: return
//
//        val notification = NotificationCompat.Builder(context!!, Constants.channelID)
//            .setContentTitle("Notification From Receiver")
//            .setContentText("Hello From Receiver")
//            .setSmallIcon(R.drawable.ic_baseline_notifications_24)
//            .setPriority(NotificationCompat.PRIORITY_HIGH)
//            .setContentIntent(pendingIntent)
//            .build()
//
//
//        val notificationManager = NotificationManagerCompat.from(context)
//
//        notificationManager.notify(Constants.notificationID, notification)
//
//        if (intent!!.action == "android.intent.action.BOOT_COMPLETED" || intent.action=="android.intent.action.DATE_CHANGED") {
//
//
//
//
//            val newIntent = Intent(context, BaseService::class.java)
//
//
//            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//                context!!.startForegroundService(newIntent)
//            } else {
//                context!!.startService(newIntent)
//            }

//            val calendar = Calendar.getInstance()
//            val alarmUtils = AlarmUtils(context)
//            alarmUtils.initRepeatingAlarm(calendar)

//println("Alarm triggered: $message")
//        }
//
//
//
//
//    }
//
//
//}