package com.example.chartpayment.ui.component.chart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.core.model.model.PaymentType

@Composable
fun CardContent(paymentType: PaymentType) {
        Row(
            modifier = Modifier
                .padding(8.dp)) {
            Text(modifier = Modifier.weight(1f), text = paymentType.label.toString(), style = MaterialTheme.typography.bodyMedium, color = Color.White)
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Right Arrow",
                tint = Color.Black, // You can change the color as needed
                modifier = Modifier
                    .size(24.dp)
                    .weight(0.1f)
            )
        }

}