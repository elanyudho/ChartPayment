package com.example.chartpayment.presentation.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.chartpayment.presentation.presentation.viewmodel.HomeViewModel
import com.example.chartpayment.ui.component.chart.PaymentItem
import com.example.core.model.model.History
import com.example.core.model.model.PaymentType
import com.github.tehras.charts.line.LineChart
import com.github.tehras.charts.line.LineChartData
import com.github.tehras.charts.line.renderer.point.FilledCircularPointDrawer
import com.github.tehras.charts.piechart.PieChart
import com.github.tehras.charts.piechart.PieChartData
import com.github.tehras.charts.piechart.animation.simpleChartAnimation
import com.github.tehras.charts.piechart.renderer.SimpleSliceDrawer

@Composable
fun Home(navController: NavHostController, homeViewModel: HomeViewModel) {
    LaunchedEffect(Unit) {
        homeViewModel.getChart()
    }
    ChartContent(navController, homeViewModel)
}

@Composable
private fun ChartContent(navController: NavHostController, homeViewModel: HomeViewModel) {
    val uiState = homeViewModel.uiState.collectAsState()

    // Render the UI based on the current state
    when (val state = uiState.value) {
        is HomeViewModel.MainUiState.Success -> {
            // Render UI for success state, e.g., display the data
            homeViewModel.setDataChart(state.data)

        }

        is HomeViewModel.MainUiState.Loading -> {
            // Render UI for loading state, e.g., show loading indicator
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Loading...")
            }
        }
        is HomeViewModel.MainUiState.GetDataChart -> {
            Column(
                modifier = Modifier
                    .padding(
                        horizontal = 16.dp,
                        vertical = 16.dp
                    )
            ) {
                Text(text = "Line Chart", style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(8.dp))
                LineChartRow(state.dataLine)
                Spacer(modifier = Modifier.height(24.dp))
                Text(text = "Donut Chart", style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(18.dp))
                PieChartRow(data = state.dataPie)
                PaymentTypeList(navController = navController, state.payments, state.pieColor)
            }
        }
    }
}

@Composable
fun LineChartRow(data: LineChartData) {
    Box(
        modifier = Modifier
            .height(250.dp)
            .fillMaxWidth()
    ) {
        LineChart(
            linesChartData = listOf(data),
            horizontalOffset = 5f,
        animation = simpleChartAnimation(),
            pointDrawer = FilledCircularPointDrawer()
        )
    }
}

@Composable
private fun PieChartRow(data: PieChartData) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
    ) {
        PieChart(
            pieChartData = data,
            sliceDrawer = SimpleSliceDrawer(
                sliceThickness = 32f
            )
        )
    }
}

@Composable
private fun PaymentTypeList(
    navController: NavHostController,
    paymentTypes: List<PaymentType>,
    color: List<Color>
) {
    LazyColumn(modifier = Modifier.padding(vertical = 4.dp).fillMaxSize()) {
        itemsIndexed(items = paymentTypes) { index, it ->
            PaymentItem(navController = navController, it, index, color = color[index])
        }
    }
}