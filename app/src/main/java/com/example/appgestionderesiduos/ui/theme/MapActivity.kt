package com.example.appgestionderesiduos

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appgestionderesiduos.ui.theme.AppGestionDeResiduosTheme

class MapActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppGestionDeResiduosTheme {
                ImageScreen()
            }
        }
    }
}

@Composable
fun ImageScreen() {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.reciclaje),
            contentDescription = "Imagen de reciclaje",
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            context.startActivity(Intent(context, MainActivity::class.java))
        }) {
            Text("Volver a la pantalla de inicio")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ImageScreenPreview() {
    AppGestionDeResiduosTheme {
        ImageScreen()
    }
}