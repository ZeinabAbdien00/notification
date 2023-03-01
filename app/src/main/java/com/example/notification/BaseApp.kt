package com.example.notification

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

class BaseApp : Application() {

//    override fun onCreate() {
//        super.onCreate()
//        createNotificationChannel()
//    }
//
//    private fun createNotificationChannel() {
//        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
//            val channel = NotificationChannel(channelID, "Tea Timer", NotificationManager.IMPORTANCE_DEFAULT)
//            val notificationManager = getSystemService(NotificationManager::class.java)
//            notificationManager.createNotificationChannel(channel)
//        }
//    }

}