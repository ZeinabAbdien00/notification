package com.example.notification

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.notification.Constants.channelID
import com.example.notification.Constants.channelName
import com.example.notification.Constants.descriptionNotification
import com.example.notification.databinding.ActivityMainBinding
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var picker : MaterialTimePicker

    private lateinit var calendar: Calendar

    private lateinit var alarmManager : AlarmManager

    private lateinit var pendingIntent: PendingIntent

    //private lateinit var baseService: BaseService

    //@RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("UnspecifiedImmutableFlag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val calendar = Calendar.getInstance()
        calendar[Calendar.HOUR_OF_DAY] = 18
        calendar[Calendar.MINUTE] = 30
        calendar[Calendar.SECOND] = 0
        val intent1 = Intent(this@MainActivity, Notification::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this@MainActivity,
            0,
            intent1,
           0)
        val am = this@MainActivity.getSystemService(ALARM_SERVICE) as AlarmManager
        am.setRepeating(AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent)


        createNotificationChannel()


  //      val scheduler = AndroidAlarmScheduler(this)
//        var alarmItem: AlarmItem? = null

        binding.submitButton.setOnClickListener {




//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                alarmItem = AlarmItem(
//                    time = LocalDateTime.now().plusSeconds(5),
//                    message = "Hello World"
//                )
//                Log.d("suzan", LocalDateTime.now().toString())
//
//            }

           // alarmItem?.let(scheduler::schedule)
        }

        binding.hideButton.setOnClickListener {
          //  alarmItem?.let(scheduler::cancel)
        }



        //اللي هيعمل ريبيت set وهباصيلها 4 متغيرات






//
//        calendar = Calendar.getInstance()
//        calendar[Calendar.HOUR_OF_DAY] = 12
//        calendar[Calendar.MINUTE] = 19
//        calendar[Calendar.SECOND] = 0
//        calendar[Calendar.MILLISECOND] = 0
//        calendar[Calendar.AM] = 1
//
//        binding.submitButton.setOnClickListener {
//
////            showTimePicker()
//
////            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
////                setAlarm()
////            }
//
//
//        }
//
//        binding.setAlarmButton.setOnClickListener {
//
////            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
////                setAlarm()
////            }
////
////            showTimePicker()
//
//        }
//        binding.hideButton.setOnClickListener {
//
////            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
////                cancelAlarm()
////            }
//
//
//        }
//

    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun cancelAlarm() {

        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = Intent(this , Notification::class.java)

        pendingIntent = PendingIntent.getBroadcast(this , 0 , intent , PendingIntent.FLAG_IMMUTABLE)

        alarmManager.cancel(pendingIntent)

        Toast.makeText(this , "alarm canceled " , Toast.LENGTH_LONG).show()

    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun setAlarm() {

        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager

        Log.d("suzan" , "top of set alarm")

        val intent = Intent(this , Notification::class.java)

        Log.d("suzan" , intent.toString())


        pendingIntent = PendingIntent.getBroadcast(this , 0 , intent , PendingIntent.FLAG_IMMUTABLE)

        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP , calendar.timeInMillis ,
            AlarmManager.INTERVAL_DAY , pendingIntent

        )


        Toast.makeText(this , "Alarm set ", Toast.LENGTH_LONG).show()

        Log.d("suzan" , "end of set alarm")


    }

    private fun showTimePicker() {

        picker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_12H)
            .setHour(12)
            .setMinute(0)
            .setTitleText("Hello")
            .build()

        picker.show(supportFragmentManager , "suzan")

        picker.addOnPositiveButtonClickListener {

            if(picker.hour>12){

                binding.selectedTime.text =
                    String.format("%02d",picker.hour - 12) + " : " +   String.format(
                        "%02d",picker.minute
                    ) + "PM"
            }else{
                binding.selectedTime.text =
                    String.format("%02d",picker.hour ) + " : " +   String.format(
                        "%02d",picker.minute
                    ) + "AM"

            }
        }




    }

    private fun createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelName = NotificationChannel(channelID,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT).apply {
                lightColor = Color.GREEN
                enableLights(true)
            }

            channelName.description = descriptionNotification

            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channelName)
        }
    }
}