package com.example.carsycompose.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
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
import com.example.carsycompose.util.dateFormatter
import com.example.carsycompose.util.decimalFormat

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
        color = Color(128, 203, 196),
        modifier = Modifier
            .height(66.dp)
            .padding(top = 12.dp)
    )
}

@Composable
private fun MonthItem(item: CostListItem.CostMonthItem){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 6.dp),
                colorFilter = ColorFilter.tint(Color(128, 203, 196))
            )

        }
        Text(
            text = item.month,
            color = Color(166, 111, 131),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }

}

@Composable
private fun GeneralItem(item: CostListItem.CostGeneralItem){
    Row(
        modifier = Modifier.height(68.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier
            .width(60.dp)
            .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawLine(
                    color = Color(128, 203, 196),
                    start = Offset(size.width / 2, 0f),
                    end = Offset(size.width / 2, size.height),
                    strokeWidth = 4.dp.toPx()
                )
            }
            Image(
                imageVector = Icons.Default.Circle,
                contentDescription = "",
                modifier = Modifier
                    .size(48.dp)
                    .fillMaxWidth()
                    .padding(top = 6.dp),
                colorFilter = ColorFilter.tint(Color(128, 203, 196))
            )

            Image(
                imageVector = item.cost.type.icon,
                contentDescription = "",
                modifier = Modifier
                    .size(24.dp)
                    .padding(top = 4.dp)
            )

        }
        Column(Modifier) {
            Text(
                text = item.cost.type.costType,
                fontSize = 20.sp,
                color = Color(128, 203, 196)
            )
            Text(
                text = item.cost.date.format(dateFormatter),
                fontSize = 12.sp,
                color = Color(128, 203, 196)
            )
        }
        Spacer(Modifier.weight(1f))
        Text(
            text = item.cost.amount.toString().format(decimalFormat) + " zł",
            modifier = Modifier.align(Alignment.CenterVertically).padding(end = 12.dp),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(128, 203, 196)
        )
    }
}