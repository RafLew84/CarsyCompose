package com.example.carsycompose.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.carsycompose.util.decimalFormat
import com.example.carsycompose.util.second
import com.example.carsycompose.util.third

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorsScreen(){
    val calculatorItems = listOf(
        "Koszt podróży" to listOf("Odległość [km]", "Cena za litr [PLN]", "Spalanie [l/100km]"),
        "Wymagane paliwo" to listOf("Odległość [km]", "Cena za litr [PLN]", "Spalanie [l/100km]"),
        "Odległość" to listOf("Paliwo [l]", "Cena za listr [PLN]", "Spalanie [l/100km]")
    )

    var expanded by remember { mutableStateOf(false) }

    var selectedText by remember { mutableStateOf(calculatorItems.first().first) }

    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    val labelData = remember { mutableStateListOf(calculatorItems.first().second) }

    var fieldTop by remember { mutableStateOf("")}
    var fieldMiddle by remember { mutableStateOf("")}
    var fieldBottom by remember { mutableStateOf("")}

    var mainResult by remember { mutableStateOf("")}
    var subResult by remember { mutableStateOf("")}

    val icon = if (expanded)
        Icons.Filled.ArrowUpward
    else
        Icons.Filled.ArrowDownward

    Column(modifier = Modifier.fillMaxSize()) {
        Column(Modifier.padding(20.dp)) {
            OutlinedTextField(
                value = selectedText,
                readOnly = true,
                onValueChange = { selectedText = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        textFieldSize = coordinates.size.toSize()
                    },
                label = { Text("Kalkulatory", fontSize = 18.sp) },
                trailingIcon = {
                    Icon(icon, "contentDescription",
                        Modifier.clickable { expanded = !expanded })
                },
                textStyle = TextStyle.Default.copy(fontSize = 24.sp, textAlign = TextAlign.Center)
            )
            CreateDropDownMenu(
                expanded = expanded,
                textFieldSize =  textFieldSize,
                calculatorItems = calculatorItems,
                onExpandedChange = {expanded = false},
                onClick = { selected ->
                    selectedText = selected
                    expanded = false
                    labelData.clear()
                    labelData.add(calculatorItems.find { it.first == selectedText }!!.second)
                }
            )
        }
        Card(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CreateOutlinedTextField(
                    value = fieldTop,
                    onValueChange = { fieldTop = it },
                    labelText = labelData.first().first()
                )
                CreateOutlinedTextField(
                    value = fieldMiddle,
                    onValueChange = { fieldMiddle = it },
                    labelText = labelData.first().second()
                )
                CreateOutlinedTextField(
                    value = fieldBottom,
                    onValueChange = { fieldBottom = it },
                    labelText = labelData.first().third()
                )
                CreateBottomLabels(
                    selectedText = selectedText,
                    mainResult = mainResult,
                    subResult = subResult
                )
            }
        }
        CreateCalculateButton(
            selectedText =  selectedText,
            calculatorItems =  calculatorItems,
            fieldTop =  fieldTop,
            fieldMiddle =  fieldMiddle,
            fieldBottom = fieldBottom,
            onMainResultChange = {mainResult = it},
            onSubResultChange = {subResult = it}
        )
    }
}

@Composable
private fun CreateDropDownMenu(
    expanded: Boolean,
    textFieldSize: Size,
    calculatorItems: List<Pair<String, List<String>>>,
    onExpandedChange: () -> Unit,
    onClick: (String) -> Unit
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { onExpandedChange() },
        modifier = Modifier
            .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
    ) {
        calculatorItems.map { it.first }.forEach { name ->
            DropdownMenuItem(
                text = {
                    Text(
                        text = name,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                onClick = {onClick(name)}
                ,modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun CreateCalculateButton(
    selectedText: String,
    calculatorItems: List<Pair<String, List<String>>>,
    fieldTop: String,
    fieldMiddle: String,
    fieldBottom: String,
    onMainResultChange: (String) -> Unit,
    onSubResultChange: (String) -> Unit
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
                onClickCalculateButton(
                    selectedText = selectedText,
                    calculatorItems = calculatorItems,
                    fieldTop = fieldTop,
                    fieldMiddle = fieldMiddle,
                    fieldBottom = fieldBottom,
                    onMainResultChange = onMainResultChange,
                    onSubResultChange = onSubResultChange
                )
            },
            modifier = Modifier.padding(top = 12.dp, end = 12.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
        ) {
            Text(
                text = "OBLICZ",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun CreateBottomLabels(
    selectedText: String,
    mainResult: String,
    subResult: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp)
    ) {
        Text(
            text = selectedText,
            modifier = Modifier.padding(start = 8.dp),
            fontSize = 28.sp
        )
        Spacer(modifier = Modifier.weight(1f))
        Column {
            Text(
                text = mainResult,
                fontSize = 22.sp,
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = subResult,
                fontSize = 16.sp,
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    labelText: String,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = value,
        singleLine = true,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = modifier.fillMaxWidth(.8f),
        label = { Text(text = labelText, fontSize = 16.sp) },
        textStyle = TextStyle.Default.copy(fontSize = 24.sp, textAlign = TextAlign.Center)
    )
}

private fun onClickCalculateButton(
    selectedText: String,
    calculatorItems: List<Pair<String, List<String>>>,
    fieldTop: String,
    fieldMiddle: String,
    fieldBottom: String,
    onMainResultChange: (String) -> Unit,
    onSubResultChange: (String) -> Unit
) {
    val isEmptyOrNull = areFieldsEmptyOrNull(fieldTop, fieldMiddle, fieldBottom)
    if (isEmptyOrNull) {
        onMainResultChange("")
        onSubResultChange("")
    }
    else {
        val result = when (selectedText) {
            calculatorItems.first().first -> travelCalculator(
                Triple(fieldTop, fieldMiddle, fieldBottom)
            )

            calculatorItems.second().first -> fuelCalculator(
                Triple(fieldTop, fieldMiddle, fieldBottom)
            )

            else -> distanceCalculator(Triple(fieldTop, fieldMiddle, fieldBottom))
        }

        onMainResultChange(result.first)
        onSubResultChange(result.second)
    }
}
private fun fuelCalculator(values: Triple<String, String, String>): Pair<String, String> {

    val distance = values.first.toDouble()
    val cost = values.second.toDouble()
    val fuelUsage = values.third.toDouble()

    val fuelCost = (distance * fuelUsage) / 100
    val fuelAmount = fuelCost * cost

    val stringMain = decimalFormat.format(fuelCost).toString()
    val stringBottom = decimalFormat.format(fuelAmount).toString()

    return Pair("$stringMain l", "$stringBottom zł")
}

private fun distanceCalculator(values: Triple<String, String, String>): Pair<String, String> {

    val fuel = values.first.toDouble()
    val price = values.second.toDouble()
    val fuelUsage = values.third.toDouble()

    val distance = fuel * price
    val totalCost = (100 * fuel) / fuelUsage

    val stringBottom = decimalFormat.format(distance).toString()
    val stringMain = decimalFormat.format(totalCost).toString()

    return Pair("$stringMain km", "$stringBottom zł")
}

private fun travelCalculator(values: Triple<String, String, String>): Pair<String, String> {

    val distance = values.first.toDouble()
    val price = values.second.toDouble()
    val fuelUsage = values.third.toDouble()

    val totalFuel = (fuelUsage / 100) * distance
    val totalPrice = totalFuel * price

    val stringBottom = decimalFormat.format(totalFuel).toString()
    val stringMain = decimalFormat.format(totalPrice).toString()

    return Pair("$stringMain zł", "$stringBottom l")
}

fun areFieldsEmptyOrNull(fieldTop: String?, fieldMiddle: String?, fieldBottom: String?): Boolean {
    return fieldTop.isNullOrEmpty() || fieldMiddle.isNullOrEmpty() || fieldBottom.isNullOrEmpty()
}

