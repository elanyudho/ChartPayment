package com.example.core.data.remote.mapper

import com.example.core.model.model.Charts
import com.example.core.abstraction.BaseMapper
import com.example.core.data.remote.response.ChartResponse
import com.example.core.model.model.History
import com.example.core.model.model.PaymentType
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

class ChartMapper : BaseMapper<ChartResponse, Charts> {
    override fun mapToDomain(raw: ChartResponse): Charts {
        val paymentTypes = ArrayList<PaymentType>()
        val months = ArrayList<Int>()

        val gson: Gson = GsonBuilder().create()

        for (chartItem in raw) {
            when (chartItem.type) {
                "donutChart" -> {
                    val chartDataList = chartItem.data as? List<*>
                    chartDataList?.let { dataList ->
                        for (chartData in dataList) {
                            val chartDataJson = gson.toJson(chartData)
                            val chartDataTyped: ChartResponse.ChartResponseItem.Data =
                                gson.fromJson(chartDataJson, object : TypeToken<ChartResponse.ChartResponseItem.Data>() {}.type)

                            val histories = ArrayList<History>()
                            val dataItems = chartDataTyped.data as? List<*>
                            dataItems?.let {
                                for (data in dataItems) {
                                    val jsonData = gson.toJson(data)
                                    val dataTyped: ChartResponse.ChartResponseItem.Data.Data =
                                        gson.fromJson(jsonData, object : TypeToken<ChartResponse.ChartResponseItem.Data.Data>() {}.type)

                                    val dataHistory = History(dataTyped?.trxDate, dataTyped?.nominal.toString())
                                    histories.add(dataHistory)
                                }
                            }
                            val dataPaymentType = PaymentType(chartDataTyped.label, chartDataTyped.percentage ?: "", histories)
                            paymentTypes.add(dataPaymentType)
                        }
                    }
                }
                "lineChart" -> {
                    val dataMap = chartItem.data as? Map<*, *>
                    dataMap?.let { data ->
                        val monthData = data["month"] as? List<Int>
                        monthData?.let {
                            months.addAll(it)
                        }
                    }
                }
            }
        }
        return Charts(paymentTypes, months)
    }


    override fun mapToRaw(domain: Charts): ChartResponse {
       return ChartResponse()
    }
}

