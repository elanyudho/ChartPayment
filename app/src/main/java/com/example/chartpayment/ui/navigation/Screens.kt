package com.example.chartpayment.ui.navigation


sealed class Screens(val route: String) {
    object Home: Screens("home_screen")
    object Detail: Screens("detail_screen")
}
