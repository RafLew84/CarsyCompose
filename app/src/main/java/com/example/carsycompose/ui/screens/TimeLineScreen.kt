package com.example.carsycompose.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.carsycompose.data.CostListItem
import com.example.carsycompose.data.DataProvider

@Composable
fun TimeLineScreen(paddingValues: PaddingValues){
    val data = remember { DataProvider.getTimeLineList(DataProvider.cars[0].costs) }
    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),
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
    Text(
        text = item.year,
        fontSize = 36.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Cyan,
        modifier = Modifier
            .height(66.dp)
            .padding(top = 12.dp)
    )
}

@Composable
private fun MonthItem(item: CostListItem.CostMonthItem){
    Row(
        modifier = Modifier.fillMaxWidth().height(40.dp)
    ){
        Box(modifier = Modifier
            .width(60.dp)
            .fillMaxHeight()
            .drawWithContent {
                drawContent()
                drawLine(
                    color = Color(128, 203, 196),
                    start = Offset(size.width / 2, 0f),
                    end = Offset(size.width / 2, size.height),
                    strokeWidth = 4.dp.toPx()
                )
            }
        ) {
            Image(
                imageVector = Icons.Default.Circle,
                contentDescription = "",
                modifier = Modifier.fillMaxWidth().padding(top = 6.dp),
                colorFilter = ColorFilter.tint(Color(128, 203, 196))
            )

        }
        Text(text = item.month, color = Color(128, 203, 196), fontSize = 24.sp)
    }

}

@Composable
private fun GeneralItem(item: CostListItem.CostGeneralItem){
        Text(text = item.cost.type.costType)
}