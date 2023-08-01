package com.example.core.data.remote.response


import com.google.gson.annotations.SerializedName

class ChartResponse : ArrayList<ChartResponse.ChartResponseItem>(){
    data class ChartResponseItem(
        @SerializedName("data")
        val data: Any,
        @SerializedName("type")
        val type: String? = ""
    ) {
        data class Data(
            @SerializedName("data")
            val `data`: List<Data?>? = listOf(),
            @SerializedName("label")
            val label: String? = "",
            @SerializedName("percentage")
            val percentage: String? = ""
        ) {
            data class Data(
                @SerializedName("nominal")
                val nominal: Int? = 0,
                @SerializedName("trx_date")
                val trxDate: String? = ""
            )
        }
    }
}