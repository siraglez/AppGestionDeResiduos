package com.example.appgestionderesiduos

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface//añadido rama estadistica (este creo que esta repe)
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appgestionderesiduos.ui.theme.AppGestionDeResiduosTheme
import java.util.Calendar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {


                    Surface() {
                        Greeting(
                            name = "usuario",
                            modifier = Modifier.padding(16.dp)
                        )
                    }



        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

        Column(
            modifier = modifier.padding(16.dp)
        ) {
            val hora = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) //usamos una variable que guarde la hora en la que se esta haciendo uso de la aplicación
            val bienvenida = when {
                hora < 13 -> "Buenos días"
                hora < 20 -> "Buenas tardes"
                else -> "Buenas noches"
            }
            //creamos una variable bienvenida que cambiara dependiendo de la hora en la que se use la aplicación

            Text(text = bienvenida)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Bienvenido $name")

            Spacer(modifier = Modifier.height(16.dp))

            BotonC()

            Spacer(modifier = Modifier.height(16.dp))

            BotonE()

            Spacer(modifier = Modifier.height(16.dp))

            BotonM()

            //añadimos los botones que nos llevaran a las diferentes pantallas de la aplicación
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
        context.startActivity(
            Intent(
                context,
                EstadisticasActivity::class.java
            )
        )
    }) {
        Text("Estadísticas")
    }
}

@Composable
fun BotonM() {
    val context = LocalContext.current
    Button(onClick = {
        context.startActivity(Intent(context, MapActivity::class.java))
    }) {
        Text("Mapa")
    }
}
