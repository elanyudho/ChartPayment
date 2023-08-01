package com.example.chartpayment.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.chartpayment.presentation.presentation.screens.Detail
import com.example.chartpayment.presentation.presentation.screens.Home
import com.example.chartpayment.presentation.presentation.viewmodel.HomeViewModel


@Composable
fun NavGraph(navController: NavHostController, homeViewModel: HomeViewModel) {

    NavHost(
        navController = navController,
        startDestination = Screens.Home.route
    )
    {
        composable(route = Screens.Home.route) {
            Home(navController, homeViewModel)
        }
        composable(
            route = "${Screens.Detail.route}/{indexPayment}",
            arguments = listOf(navArgument("indexPayment") { type = NavType.IntType })
        ) {
            val index = it.arguments?.getInt("indexPayment") ?: 0

            Detail(navController, index = index, homeViewModel)
        }
    }
}