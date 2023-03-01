package com.example.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import java.time.ZoneId
import java.util.*

class AndroidAlarmScheduler(
    private val context: Context,
) : AlarmScheduler {

    private val alarmManager = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        context.getSystemService(AlarmManager::class.java)
    } else {
        TODO("VERSION.SDK_INT < M")
    }

    override fun schedule(item: AlarmItem) {
        val intent = Intent(context, Notification::class.java).apply {
            putExtra("EXTRA_MESSAGE", item.message)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            loop@ while (true) {

                val pendingIntent = PendingIntent.getBroadcast(
                    context,
                    item.hashCode(),
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )

                val calendar: Calendar = Calendar.getInstance()
                calendar[Calendar.HOUR_OF_DAY] = item.time.hour
                calendar[Calendar.MINUTE] =  item.time.minute
                calendar[Calendar.SECOND] =  item.time.second

                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    AlarmManager.INTERVAL_DAY,
                    pendingIntent)

                //if (item.time.hour.toString() == "18" && item.time.minute.toString() == "12") {


                Log.d("suzan", item.time.toString())
                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    item.time.atZone(ZoneId.systemDefault()).toEpochSecond() * 1000,
                    pendingIntent
                )


                break@loop
                //}
            }
        }
    }

    override fun cancel(item: AlarmItem) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.cancel(
                PendingIntent.getBroadcast(
                    context,
                    item.hashCode(),
                    Intent(context, Notification::class.java),
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )
            )
        }
    }
}
