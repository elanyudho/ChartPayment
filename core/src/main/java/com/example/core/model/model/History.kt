package com.example.core.model.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class History(
    val date: String? = "",
    val nominal: String = "",
): Parcelable