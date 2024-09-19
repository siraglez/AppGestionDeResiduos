package com.example.appgestionderesiduos.ui.theme

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp




@Composable
fun Estadisticas() {
    val barData = createBarData()
    val lineData = createLineData()
    val pieData = createPieData()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Estadísticas de Residuos", style = MaterialTheme.typography.headlineMedium, fontSize = 30.sp, fontWeight = FontWeight.Bold)
        AndroidView(
            factory = { context ->
                BarChart(context).apply {
                    data = barData
                    description.isEnabled = false
                    setDrawGridBackground(false)
                    setDrawBarShadow(false)
                    setDrawValueAboveBar(true)
                    setPinchZoom(true)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .border(1.dp, Color.Black)
        )
        AndroidView(
            factory = { context ->
                LineChart(context).apply {
                    data = lineData
                    description.isEnabled = false
                    setDrawGridBackground(false)
                    setPinchZoom(true)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .border(1.dp, Color.Black)
        )
        AndroidView(
            factory = { context ->
                PieChart(context).apply {
                    data = pieData
                    description.isEnabled = false
                    setUsePercentValues(true)
                    setDrawHoleEnabled(false)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .border(1.dp, Color.Black)
        )
    }
}



//GRAFICO BARRAS
@Composable
fun createBarData(): BarData {
    val entries = listOf(
        BarEntry(1f, 10f),
        BarEntry(2f, 20f),
        BarEntry(3f, 30f),
        BarEntry(4f, 40f),
        BarEntry(5f, 50f)
    )
    val dataSet = BarDataSet(entries, "Residuos").apply {
        colors = listOf(
            Color(0xFF1E88E5).toArgb(),
            Color(0xFFD32F2F).toArgb(),
            Color(0xFF388E3C).toArgb(),
            Color(0xFFFBC02D).toArgb(),
            Color(0xFF8E24AA).toArgb()
        )
        valueTextColor = Color.White.toArgb()
        valueTextSize = 12f
    }
    return BarData(dataSet)
}



//GRAFICO LINEAS
@Composable
fun createLineData(): LineData {
    val entries1 = listOf(
        Entry(1f, 15f),
        Entry(2f, 25f),
        Entry(3f, 35f),
        Entry(4f, 45f),
        Entry(5f, 55f)
    )
    val dataSet1 = LineDataSet(entries1, "Tendencia de Residuos 1").apply {
        color = Color(0xFF1E88E5).toArgb()
        valueTextColor = Color.White.toArgb()
        valueTextSize = 12f
        lineWidth = 2f
        setDrawCircles(true)
        circleColors = listOf(Color(0xFFD32F2F).toArgb())
    }

    val entries2 = listOf(
        Entry(1f, 50f),
        Entry(2f, 40f),
        Entry(3f, 30f),
        Entry(4f, 20f),
        Entry(5f, 10f)
    )
    val dataSet2 = LineDataSet(entries2, "Tendencia de Residuos 2").apply {
        color = Color(0xFF388E3C).toArgb()
        valueTextColor = Color.White.toArgb()
        valueTextSize = 12f
        lineWidth = 2f
        setDrawCircles(true)
        circleColors = listOf(Color(0xFFFBC02D).toArgb())
    }

    return LineData(dataSet1, dataSet2)
}



//QUESITO (GRAFICO DE TORTA)
@Composable
fun createPieData(): PieData {
    val entries = listOf(
        PieEntry(30f, "Orgánico"),
        PieEntry(25f, "Plástico"),
        PieEntry(20f, "Papel"),
        PieEntry(15f, "Vidrio"),
        PieEntry(10f, "Metal")
    )
    val dataSet = PieDataSet(entries, "Tipos de Residuos").apply {
        colors = listOf(
            Color(0xFF1E88E5).toArgb(),
            Color(0xFFD32F2F).toArgb(),
            Color(0xFF388E3C).toArgb(),
            Color(0xFFFBC02D).toArgb(),
            Color(0xFF8E24AA).toArgb()
        )
        valueTextColor = Color.White.toArgb()
        valueTextSize = 12f
    }
    return PieData(dataSet)
}

@Preview(showBackground = true)
@Composable
fun EstadisticasPreview() {
    AppGestionDeResiduosTheme {
        Estadisticas()
    }
}