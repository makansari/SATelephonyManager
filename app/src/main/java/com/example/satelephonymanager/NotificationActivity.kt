package com.example.satelephonymanager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_notification.*

class NotificationActivity : AppCompatActivity() {

    var channelId = "4567"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        var noficationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        buttonNotification.setOnClickListener {


            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                var notficationChannel = NotificationChannel(channelId,"sports",NotificationManager.IMPORTANCE_HIGH)
                noficationManager.createNotificationChannel(notficationChannel)

                var myIntent = Intent(this,MainActivity::class.java)
                var pi = PendingIntent.getActivity(this,999,myIntent,0)

                var notificationBuilder = NotificationCompat.Builder(this,channelId)
                        .setSmallIcon(R.drawable.icon)
                        .setContentTitle("ANSARI NOTIFICATION")
                        .setContentText("This is my notifiction message")
                        .setContentIntent(pi)
                        .setAutoCancel(true)

                noficationManager.notify(123,notificationBuilder.build())
                
            }
            else
            {
                var notificationBuilder = NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.cam)
                        .setContentTitle("ANSARI NOTIFICATION")
                        .setContentText("This is my notifiction message")

                noficationManager.notify(123,notificationBuilder.build())
            }



        }
    }
}