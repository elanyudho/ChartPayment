package com.example.core.model.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Charts(
    val dataPieChart: List<PaymentType> = listOf(),
    val dataLineChart: List<Int> = listOf()
): Parcelable