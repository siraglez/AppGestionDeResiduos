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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview

class CalendarActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalendarScreen()
        }
    }

    @Composable
    fun CalendarScreen() {
        val selectedDate = remember { mutableStateOf(0L) } // Uso de `remember` para recordar la fecha seleccionada
        val currentDate = System.currentTimeMillis() // Fecha actual para comparación

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
                        minDate = currentDate // Deshabilitar fechas anteriores
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
                if (selectedDate.value > currentDate) {
                    // Establecer recordatorio (ejemplo, agregar AlarmManager aquí)
                    setReminder(selectedDate.value)
                    Toast.makeText(this@CalendarActivity, "Recordatorio establecido para ${Date(selectedDate.value)}", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this@CalendarActivity, "Selecciona una fecha futura", Toast.LENGTH_LONG).show()
                }
            }) {
                Text(text = "Establecer Recordatorio")
            }
        }
    }

    fun setReminder(timeInMillis: Long) {
        // Aquí puedes integrar AlarmManager o WorkManager para establecer una alarma
        // Esta es una implementación básica utilizando AlarmManager
        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, ReminderReceiver::class.java) // Crear intent para el BroadcastReceiver

        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, timeInMillis, pendingIntent)
    }

    @Preview(showBackground = true)
    @Composable
    fun CalendarScreenPreview() {
        CalendarScreen()
    }
}