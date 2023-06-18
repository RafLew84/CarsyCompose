package com.example.carsycompose.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.carsycompose.data.Car
import com.example.carsycompose.data.CostType
import com.example.carsycompose.data.DataProvider
import com.example.carsycompose.util.decimalFormat

@Composable
fun OverviewScreen(paddingValues: PaddingValues){

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .verticalScroll(rememberScrollState())
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            CreateOuterCardTitle("Podsumowanie kosztów")

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ){
                items(DataProvider.cars.size){ index ->
                    Card(
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(8.dp)
                    ) {
                        CreateInnerCardTitle(title = DataProvider.cars[index].name, width = 250)
                        Column(
                            modifier = Modifier
                                .width(250.dp)
                                .padding(bottom = 8.dp)
                        ) {
                            CostType.values().forEach {
                                CreateInnerCardRow(
                                    it.costType,
                                    costValue(DataProvider.cars[index], it) + " zł"
                                )
                            }
                        }
                    }
                }
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            CreateOuterCardTitle(title = "Garaż")

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ){
                items(DataProvider.cars.size){ index ->
                    Card(
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(8.dp)
                    ) {
                        CreateInnerCardTitle(title = DataProvider.cars[index].name, width = 200)
                        Column(
                            modifier = Modifier
                                .width(200.dp)
                                .padding(bottom = 8.dp)
                        ) {
                            val data: List<Pair<String, String>> = getCarDetails(index)
                            data.forEach {
                                CreateInnerCardRow(
                                    firstText = it.first,
                                    secondText = it.second
                                )
                            }
                        }
                    }
                }
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            CreateOuterCardTitle("Koszty całkowite")

            Card(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                ) {
                    CostType.values().forEach {
                        CreateInnerCardRow(it.costType, totalValue(it) + " zł")
                    }
                }
            }
        }
    }
}

@Composable
private fun CreateInnerCardTitle(title: String, width: Int) {
    Box(
        modifier = Modifier
            .width(width.dp)
            .background(Color(166, 111, 131))
    ) {
        Text(
            text = title,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            style = TextStyle.Default.copy(Color.Black)
        )
    }
}

private fun getCarDetails(index: Int): List<Pair<String, String>> {
    var data: List<Pair<String, String>>
    DataProvider.cars[index].apply {
        data = listOf(
            "Marka:" to brand,
            "Model:" to model,
            "Rok:" to yearOfProduction.toString(),
            "Suma:" to decimalFormat.format(costs.sumOf { cost -> cost.amount })
                .toString() + " zł"
        )
    }
    return data
}

@Composable
private fun CreateInnerCardRow(firstText: String, secondText: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = firstText,
            fontSize = 16.sp,
            modifier = Modifier.padding(start = 4.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = secondText,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(end = 4.dp)
        )
    }
}

@Composable
private fun CreateOuterCardTitle(title: String) {
    Box(
        modifier = Modifier
            .background(Color(128, 203, 196))
            .fillMaxWidth()
    ) {
        Text(
            text = title,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            style = TextStyle.Default.copy(Color.Black)
        )
    }
}

private fun totalValue(costType: CostType): String {
    return decimalFormat.format(
        DataProvider.cars
            .flatMap { it.costs }
            .filter { it.type == costType }
            .sumOf { it.amount }).toString()
}

private fun costValue(item: Car, costType: CostType) =
    decimalFormat.format(item.costs
        .filter { it.type == costType }
        .sumOf { it.amount }).toString()