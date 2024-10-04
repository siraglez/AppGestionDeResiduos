package com.example.appgestionderesiduos

import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

class MapActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WebViewExample()
        }
    }
}

@Composable
fun WebViewExample() {
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                //Habilitar JavaScript
                settings.javaScriptEnabled = true
                //Habilitar almacenamiento local (DOM storage)
                settings.domStorageEnabled = true
                //Forzar que el contenido cargue en la aplicación
                webViewClient = WebViewClient()
                //Registrar el log para asegurarnos de que se está cargando contenido
                Log.d("WebViewExample", "WebView is loading OpenStreetMap content")
                //argar el mapa de OpenStreetMap
                loadData(
                    """
                    <!DOCTYPE html>
                    <html>
                    <head>
                        <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
                        <meta charset="utf-8" />
                        <title>Mapa OpenStreetMap</title>
                        <style>
                            #map {
                                height: 100%;
                                width: 100%;
                            }
                            html, body {
                                height: 100%;
                                margin: 0;
                                padding: 0;
                            }
                        </style>
                    </head>
                    <body>
                        <div id="map"></div>
                        <script src="https://unpkg.com/leaflet@1.7.1/dist/leaflet.js"></script>
                        <link rel="stylesheet" href="https://unpkg.com/leaflet@1.7.1/dist/leaflet.css" />
                        <script>
                            var map = L.map('map').setView([-34.0, 151.0], 10);
                            L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
                                attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
                            }).addTo(map);
                            L.marker([-34.0, 151.0]).addTo(map)
                                .bindPopup('Marcador en Sídney')
                                .openPopup();
                        </script>
                    </body>
                    </html>
                    """.trimIndent(),
                    "text/html",
                    "UTF-8"
                )
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}
