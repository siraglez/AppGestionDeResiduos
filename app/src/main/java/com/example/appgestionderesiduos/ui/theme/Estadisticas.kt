package com.example.appgestionderesiduos.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Estadisticas() {
    val entries = listOf(
        BarEntry(1f, 10f),
        BarEntry(2f, 20f),
        BarEntry(3f, 30f)
    )
    val dataSet = BarDataSet(entries, "Residuos")
    val barData = BarData(dataSet)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Estadísticas de Residuos", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        AndroidView(
            factory = { context ->
                BarChart(context).apply {
                    data = barData
                    description.isEnabled = false
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EstadisticasPreview() {
    AppGestionDeResiduosTheme {
        Estadisticas()
    }
}