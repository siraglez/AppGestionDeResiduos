package com.example.appgestionderesiduos

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.widget.TimePicker
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
import androidx.compose.ui.platform.LocalContext

class CalendarActivity : ComponentActivity() {

    private val reminders = mutableStateListOf<Reminder>() //Lista para guardar los recordatorios

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalendarScreen()
        }
    }

    @Composable
    fun CalendarScreen() {
        var selectedDate = remember { mutableStateOf(0L) } // Uso de `remember` para recordar la fecha seleccionada
        var selectedHour = remember { mutableStateOf(0) }
        var selectedMinute = remember { mutableStateOf(0) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // CalendarView para seleccionar la fecha
            AndroidView(
                modifier = Modifier.weight(1f),
                factory = { context ->
                    CalendarView(context).apply {
                        setOnDateChangeListener { _, year, month, dayOfMonth ->
                            val calendar = Calendar.getInstance()
                            calendar.set(year, month, dayOfMonth)
                            selectedDate.value = calendar.timeInMillis
                        }
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            //TimePicker para seleccionar la hora
            AndroidView(
                factory = { context ->
                    TimePicker(context).apply {
                        setOnTimeChangedListener { _, hourOfDay, minute ->
                        selectedHour.value = hourOfDay
                        selectedMinute.value = minute
                        }
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Fila con botón para establecer recordatorios y botón para volver a la página principal
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = {
                    if (selectedDate.value != 0L) {
                        // Combinar fecha y hora seleccionadas
                        val calendar = Calendar.getInstance().apply {
                            timeInMillis = selectedDate.value
                            set(Calendar.HOUR_OF_DAY, selectedHour.value)
                            set(Calendar.MINUTE, selectedMinute.value)
                            set(Calendar.SECOND, 0)
                        }

                        val reminderTime = calendar.timeInMillis
                        reminders.add(Reminder(reminderTime)) // Guardar el recordatorio en la lista
                        setReminder(reminderTime)

                        Toast.makeText(this@CalendarActivity, "Recordatorio establecido para ${Date(reminderTime)}", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this@CalendarActivity, "Selecciona una fecha y hora", Toast.LENGTH_LONG).show()
                    }
                }) {
                    Text(text = "Establecer Recordatorio")
                }

                Spacer(modifier = Modifier.width(8.dp))

                Button(onClick = {
                    finish() // Finalizar la actividad actual y volver a la actividad anterior
                }) {
                    Text(text = "Página principal")
                }
            }


            Spacer(modifier = Modifier.height(16.dp))

            //Mostrar todos los recordatorios programados
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

//Clase para representar un recordatorio
data class Reminder(val timeInMillis: Long)
