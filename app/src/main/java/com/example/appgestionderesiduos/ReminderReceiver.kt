package com.example.appgestionderesiduos


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.app.NotificationChannel
import android.app.NotificationManager
import androidx.core.app.NotificationCompat

class ReminderReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val channelId = "reminder_channel"
            val channelName = "Recordatorio de Reciclaje"
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
                notificationManager.createNotificationChannel(channel)
            }

            val notification = NotificationCompat.Builder(context, channelId)
                .setContentTitle("Recordatorio de Reciclaje")
                .setContentText("Es hora de reciclar en la fecha y hora que seleccionaste.")
                .setSmallIcon(R.drawable.ic_launcher_foreground) // Asegúrate de tener un ícono en tu proyecto
                .setAutoCancel(true)
                .build()

            notificationManager.notify(1, notification)
        }
    }
}