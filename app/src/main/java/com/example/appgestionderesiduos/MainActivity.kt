package com.example.appgestionderesiduos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.appgestionderesiduos.ui.theme.AppGestionDeResiduosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppGestionDeResiduosTheme {
                Surface(color = MaterialTheme.colorScheme.background) {

                    // Aquí puedes agregar el contenido principal de tu aplicación

                }
            }
        }
    }
}