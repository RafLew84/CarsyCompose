package com.example.carsycompose.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.carsycompose.data.CostListItem
import com.example.carsycompose.data.DataProvider

@Composable
fun TimeLineScreen(paddingValues: PaddingValues){
    val data = remember { DataProvider.getTimeLineList(DataProvider.cars[0].costs) }
    LazyColumn(
        modifier = Modifier.padding(paddingValues).fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(data.size){index ->
            when(data[index]){
                is CostListItem.CostYearItem -> YearItem(item = data[index] as CostListItem.CostYearItem)
                is CostListItem.CostMonthItem -> MonthItem(item = data[index] as CostListItem.CostMonthItem)
                is CostListItem.CostGeneralItem -> GeneralItem(item = data[index] as CostListItem.CostGeneralItem)
            }
        }
    }
}
@Composable
private fun YearItem(item: CostListItem.CostYearItem){
    Text(text = item.year)
}

@Composable
private fun MonthItem(item: CostListItem.CostMonthItem){
    Text(text = item.month)
}

@Composable
private fun GeneralItem(item: CostListItem.CostGeneralItem){
    Text(text = item.cost.type.costType)
}