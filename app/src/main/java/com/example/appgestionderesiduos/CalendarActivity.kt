package com.example.appgestionderesiduos

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
import androidx.compose.ui.text.style.TextAlign


class CalendarActivity : ComponentActivity() {

    private val reminders = mutableStateListOf<Reminder>() // Lista para guardar los recordatorios

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadReminders() //Cargar los recordatorios al iniciar la actividad
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
                            // Guardar el recordatorio en la lista
                            reminders.add(Reminder(selectedDate.value)) // Guardar el recordatorio
                            saveReminder(selectedDate.value) // Guardar el recordatorio en SharedPreferences
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
                    },
                        modifier = Modifier.width(140.dp) //Limitar el ancho del botón
                    ) {
                        Text(
                            text = "Establecer Recordatorio",
                            maxLines = 2, //Dividir el texto en dos líneas si es necesario
                            modifier = Modifier.fillMaxWidth(), //Asegurarse de que el texto ocupe el ancho del botón
                            textAlign = TextAlign.Center //Centrar el texto horizontalmente
                        )
                    }// Botón para volver a la página principal
                    Button(onClick = {
                        val intent = Intent(this@CalendarActivity, MainActivity::class.java)
                        startActivity(intent)
                    },
                        modifier = Modifier
                            .width(140.dp) //Limitar el ancho del botón
                    ) {
                        Text(
                            text = "Volver a la página principal",
                            maxLines = 3, //Permitir que el texto se divida en dos líneas
                            modifier = Modifier.fillMaxWidth(), //Asegurarse que el texto ocupe el ancho disponible
                            textAlign = TextAlign.Center //Centrar el texto horizontalmente
                        )
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


    //Función para guardar el recordatorio
    fun saveReminder(timeInMillis: Long) {
        val sharedPref = getSharedPreferences("reminders", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putLong("reminder_$timeInMillis", timeInMillis)
            apply()
        }
    }

    //Función para cargar los recordatorios guardados
    fun loadReminders() {
        val sharedPref = getSharedPreferences("reminders", Context.MODE_PRIVATE)
        val allEntries = sharedPref.all
        for ((key, value) in allEntries) {
            if (key.startsWith("reminder_")) {
                val timeInMillis = value as Long
                reminders.add(Reminder(timeInMillis)) //Añadir el recordatorio cargado a la lista
            }
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