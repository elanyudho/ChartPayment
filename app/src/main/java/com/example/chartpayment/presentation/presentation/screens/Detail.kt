package com.example.chartpayment.presentation.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.chartpayment.presentation.presentation.viewmodel.HomeViewModel
import com.example.core.model.model.History

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Detail(navController: NavController, index: Int, homeViewModel: HomeViewModel) {
    Column() {
        TopAppBar(
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Go back to home")
                }
            },
            title = { Text(text = "Detail Transaksi") }
        )
        Spacer(modifier = Modifier.height(4.dp))
        DetailContent(homeViewModel = homeViewModel, index)  
    }
}

@Composable
fun DetailContent(homeViewModel: HomeViewModel, index: Int) {
    val uiState = homeViewModel.uiState.collectAsState()

    when (val state = uiState.value) {
        is HomeViewModel.MainUiState.GetDataChart -> {
            val data = state.payments
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)) {
                Histories(modifier = Modifier, histories = data[index].histories, state.pieColor[index])
            }
        }

        else -> {}
    }
}

@Composable
private fun Histories(
    modifier: Modifier = Modifier, histories: List<History>, color: Color
) {
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items(items = histories) {
            HistoryItem(it, color)
        }
    }


}

@Composable
fun HistoryContent(history: History) {
    Row(
        modifier = Modifier
            .padding(12.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(12.dp)
        ) {
            Text(
                text = "Waktu Transaksi: ${history.date}", style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
            )
            Spacer(modifier = Modifier.heightIn(8.dp))
            Text(
                text = "Nominal: ${history.nominal}", style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
            )
        }
    }
}

@Composable
fun HistoryItem(history: History, color: Color) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = color
        ), modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        HistoryContent(history)
    }
}