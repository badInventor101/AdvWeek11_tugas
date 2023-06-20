package com.example.advweek4_kp_c.view

import android.Manifest
import android.app.Notification
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.advweek4_kp_c.R
import com.example.advweek4_kp_c.util.createNotificationChannel

class MainActivity : AppCompatActivity() {

    init {
        instance = this
    }
    companion object{
        private var instance:MainActivity ?= null
        fun showNotification(title:String, content:String, icon:Int) {
            val channelId = "${instance?.packageName}-${instance?.getString(R.string.app_name)}"

            val builder = NotificationCompat.Builder(instance!!.applicationContext,channelId).apply {
                this.setSmallIcon(icon)
                this.setContentTitle(title)
                this.setContentText(content)
                this.setStyle(NotificationCompat.BigTextStyle())
                this.priority = NotificationCompat.PRIORITY_DEFAULT
                this.setAutoCancel(true)

            }

            val manager = NotificationManagerCompat.from(instance!!.applicationContext)
            if (ActivityCompat.checkSelfPermission(
                    instance!!.applicationContext,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {

                return
            }
            manager.notify(1001, builder.build())
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNotificationChannel(this,
            NotificationManagerCompat.IMPORTANCE_DEFAULT, false,
            getString(R.string.app_name), "App notification channel.")

    }
}