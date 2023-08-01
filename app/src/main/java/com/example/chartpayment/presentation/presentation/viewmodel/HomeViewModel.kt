package com.example.chartpayment.presentation.presentation.viewmodel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewModelScope
import com.example.core.abstraction.BaseViewModel
import com.example.core.abstraction.UseCase
import com.example.core.dispatcher.DispatcherProvider
import com.example.core.model.model.Charts
import com.example.core.model.model.PaymentType
import com.example.core.model.usecase.GetChartsUseCase
import com.example.core.util.extension.onSuccess
import com.github.tehras.charts.line.LineChartData
import com.github.tehras.charts.line.renderer.line.SolidLineDrawer
import com.github.tehras.charts.piechart.PieChartData
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val getChartsUseCase: GetChartsUseCase
) : BaseViewModel<HomeViewModel.MainUiState>() {

    private val usedColors = mutableListOf<Color>()

    private var colors = mutableListOf(
        Color(0XFFF44336),
        Color(0XFFE91E63),
        Color(0XFF9C27B0),
        Color(0XFF673AB7),
        Color(0XFF3F51B5),
        Color(0XFF03A9F4),
        Color(0XFF009688),
        Color(0XFFCDDC39),
        Color(0XFFFFC107),
        Color(0XFFFF5722),
        Color(0XFF795548),
        Color(0XFF9E9E9E),
        Color(0XFF607D8B)
    )

    sealed class MainUiState {
        object Loading : MainUiState()
        data class Success(val data: Charts) : MainUiState()
        data class GetDataChart(val dataLine: LineChartData, val dataPie: PieChartData, val payments: List<PaymentType>, val pieColor: List<Color>) : MainUiState()
    }

    fun getChart() {
        viewModelScope.launch(dispatcherProvider.io) {
            getChartsUseCase.run(UseCase.None)
                .onSuccess {
                    withContext(dispatcherProvider.main) {
                        _uiState.value = MainUiState.Success(it)
                    }
                }
        }
    }

    fun setDataChart(data: Charts) {
        viewModelScope.launch(dispatcherProvider.io) {
            //linechart
            val dataLineList = ArrayList<LineChartData.Point>()
            var month = 1
            for (i in data.dataLineChart) {
                dataLineList.add(LineChartData.Point(i.toFloat(), month.toString()))
                month += 1
            }
            val lineChartData = LineChartData(
                points = dataLineList,
                lineDrawer = SolidLineDrawer(
                    color = Color(0xFF00FF00)
                )
            )

            //piechart
            val dataPieList = ArrayList<PieChartData.Slice>()
            val chosenColor = ArrayList<Color>()
            for ((index, data) in data.dataPieChart.withIndex()) {
                val color = colors[index]
                chosenColor.add(color)
                dataPieList.add(PieChartData.Slice(data.percentage.toFloat(), color))
            }
            val pieChartData = PieChartData(
                slices = dataPieList
            )

            withContext(dispatcherProvider.main) {
                _uiState.value = MainUiState.GetDataChart(lineChartData, pieChartData, data.dataPieChart, chosenColor)
            }

        }
    }
    override val initialUiState: MainUiState
        get() = MainUiState.Loading
}