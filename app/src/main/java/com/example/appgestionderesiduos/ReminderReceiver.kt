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

            // Obtener el identificador del recordatorio desde el intent
            val reminderId = intent?.getLongExtra("reminder_id", 0L) ?: 0L

            val notification = NotificationCompat.Builder(context, channelId)
                .setContentTitle("Recordatorio de Reciclaje")
                .setContentText("Es hora de reciclar en la fecha que seleccionaste.")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setAutoCancel(true)
                .build()

            // Usar el `reminderId` como identificador único para la notificación
            notificationManager.notify(reminderId.toInt(), notification)
        }
    }
}
