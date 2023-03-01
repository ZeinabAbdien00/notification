package com.example.notification

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import java.util.*

@SuppressLint("UnspecifiedImmutableFlag")
class AlarmUtils(context: Context) {
    private var mContext = context
    private var alarmMgr: AlarmManager? = null
    private var alarmIntent: PendingIntent

    init {
        alarmMgr = mContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmIntent = Intent(mContext, Notification::class.java).let { mIntent ->
            // if you want more than one notification use different requestCode
            // every notification need different requestCode
            PendingIntent.getBroadcast(mContext, 100, mIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        }
    }

    fun initRepeatingAlarm(calendar: Calendar) {
        calendar.apply {
            set(Calendar.HOUR_OF_DAY, 10)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
        }

        alarmMgr?.set(AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            alarmIntent)
    }
}