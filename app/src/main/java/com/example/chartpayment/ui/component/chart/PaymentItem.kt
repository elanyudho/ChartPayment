package com.example.chartpayment.ui.component.chart

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.chartpayment.ui.navigation.Screens
import com.example.core.model.model.PaymentType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentItem(navController: NavController, paymentType: PaymentType, index: Int, color: Color) {
    Card(

        colors = CardDefaults.cardColors(
            containerColor = color
        ),
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
        onClick = {
            navController.navigate("${Screens.Detail.route}/$index")
        }
    ) {
        CardContent(paymentType)
    }
}