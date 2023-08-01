package com.example.core.model.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PaymentType(
    val label: String? = "",
    val percentage: String = "",
    val histories: List<History> = listOf(),

): Parcelable