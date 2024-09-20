package com.example.appgestionderesiduos

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import java.util.*
import android.widget.CalendarView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.mutableStateListOf


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
                            // Para guardar la fecha seleccionada al inicio del día (00:00 horas)
                            val calendar = Calendar.getInstance()
                            calendar.set(year, month, dayOfMonth, 0, 0, 0)
                            calendar.set(Calendar.MILLISECOND, 0)
                            selectedDate.value = calendar.timeInMillis
                        }
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Botón para Establecer Recordatorio
                Button(onClick = {
                    if (selectedDate.value != 0L) {
                        // Guardar el recordatorio en la lista y establecer el recordatorio
                        reminders.add(Reminder(selectedDate.value)) // Guardar el recordatorio
                        setReminder(selectedDate.value) // Establecer la alarma
                        Toast.makeText(
                            this@CalendarActivity,
                            "Recordatorio establecido para ${Date(selectedDate.value)}",
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


                Spacer(modifier = Modifier.width(8.dp))

                // Botón para Volver a la página principal
                Button(onClick = {
                    // Crear un intent para ir a la otra clase/actividad
                    val intent = Intent(this@CalendarActivity, MainActivity::class.java)
                    startActivity(intent) // Iniciar la actividad nueva
                }) {
                    Text(text = "Volver a la página principal")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Mostrar todos los recordatorios programados
            Text(text = "Recordatorios programados:")
            reminders.forEach { reminder ->
                Text(text = "Recordatorio para: ${Date(reminder.timeInMillis)}")
            }
        }
    }

    //Función para establecer el recordatorio
    fun setReminder(timeInMillis: Long) {
        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, ReminderReceiver::class.java).apply {
            putExtra("reminder_id", timeInMillis) // Pasar el identificador del recordatorio
        }
        val pendingIntent = PendingIntent.getBroadcast(this, timeInMillis.toInt(), intent, PendingIntent.FLAG_IMMUTABLE)
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, timeInMillis, pendingIntent)
    }

    //Función para guardar el recordatorio
    fun saveReminder(timeInMillis: Long) {
        val sharedPref = getSharedPreferences("reminders", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putLong("reminder_$timeInMillis", timeInMillis)
            apply()
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun CalendarScreenPreview() {
        CalendarScreen()
    }
}

// Clase para representar un recordatorio
data class Reminder(val timeInMillis: Long)