package com.example.appgestionderesiduos

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import java.util.*
import android.widget.CalendarView
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview

class CalendarActivity : ComponentActivity() {

    private val reminders = mutableStateListOf<Reminder>() // Lista para guardar los recordatorios


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalendarScreen()
        }
    }

    @Composable
    fun CalendarScreen() {
        var selectedDate = remember { mutableStateOf(0L) } // Uso de remember para recordar la fecha seleccionada

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // CalendarView en Compose
            AndroidView(
                modifier = Modifier.weight(1f),
                factory = { context ->
                    CalendarView(context).apply {
                        setOnDateChangeListener { _, year, month, dayOfMonth ->
                            // Para guardar la fecha seleccionada
                            val calendar = Calendar.getInstance()
                            calendar.set(year, month, dayOfMonth)
                            selectedDate.value = calendar.timeInMillis
                        }
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                if (selectedDate.value != 0L) {
                    Toast.makeText(
                        this@CalendarActivity,
                        "Recordatorio para ${Date(selectedDate.value)}",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(
                        this@CalendarActivity,
                        "Selecciona una fecha primero",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }) {
                Text(text = "Establecer Recordatorio")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Mostrar todos los recordatorios programados
            Text(text = "Recordatorios programados:")
            reminders.forEach { reminder ->
                Text(text = "Recordatorio para: ${Date(reminder.timeInMillis)}")
            }
        }
    }

    fun setReminder(timeInMillis: Long) {
        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, ReminderReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, timeInMillis, pendingIntent)
    }

    @Preview(showBackground = true)
    @Composable
    fun CalendarScreenPreview() {
        CalendarScreen()
    }
}

// Clase para representar un recordatorio
data class Reminder(val timeInMillis: Long)