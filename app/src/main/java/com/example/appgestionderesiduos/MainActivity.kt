package com.example.appgestionderesiduos

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appgestionderesiduos.ui.theme.AppGestionDeResiduosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppGestionDeResiduosTheme {
                Surface (color = Color.White) {
                    Greeting(
                        name = "usuario",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }

        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

        Column(
            modifier = modifier.padding(16.dp)
        ) {
            Text(
                text = "Bienvenido a la app de reciclaje $name!"
            )

            Spacer(modifier = Modifier.height(16.dp))

            BotonC()

            Spacer(modifier = Modifier.height(16.dp))

            BotonE()

            Spacer(modifier = Modifier.height(16.dp))

            BotonM()
        }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppGestionDeResiduosTheme {
        Greeting("usuario")
    }
}

@Composable
fun BotonC() {
    val context = LocalContext.current
    Button(onClick = {
        context.startActivity(
            Intent(
                context,
                CalendarActivity::class.java
            )
        )
    }) {
        Text("Calendario")
    }
}

@Composable
fun BotonE(){
    val context = LocalContext.current
    Button(onClick = {
        context.startActivity(Intent(context, MainActivity::class.java)) //cambiar a la clase para las estadisticas
    }) {
        Text("Estadisticas")
    }
}

@Composable
fun BotonM(){
    val context = LocalContext.current
    Button(onClick = {
        context.startActivity(Intent(context, MainActivity::class.java)) //cambiar a la clase para el mapa
    }) {
        Text("Mapa")

    }
}


