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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.carsycompose.ui.theme.CarsyComposeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorsScreen(){
    var expanded by remember { mutableStateOf(false) }
    val names by remember { mutableStateOf(listOf("Koszt podróży", "Odległość", "Wymagane paliwo")) }
    var selectedText by remember { mutableStateOf(names.first()) }

    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (expanded)
        Icons.Filled.ArrowUpward
    else
        Icons.Filled.ArrowDownward

    Column(modifier = Modifier.fillMaxSize()) {
        Column(Modifier.padding(20.dp)) {
            OutlinedTextField(
                value = selectedText,
                readOnly = true,
                onValueChange = { name -> selectedText = name },
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
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
            ) {
                names.forEach { name ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = name,
                                fontSize = 24.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                        },
                        onClick = {
                            selectedText = name
                            expanded = false
//                            data.clear()
//                            data.add(DataProvider.getTimeLineList(cars.find { it.name == selectedText }!!.costs))
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
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
                OutlinedTextField(
                    value = selectedText,
                    singleLine = true,
                    onValueChange = { name -> selectedText = name },
                    modifier = Modifier
                        .fillMaxWidth(.8f),
                    label = { Text("a", fontSize = 18.sp) },
                    textStyle = TextStyle.Default.copy(fontSize = 24.sp, textAlign = TextAlign.Center)
                )
                OutlinedTextField(
                    value = selectedText,
                    singleLine = true,
                    onValueChange = { name -> selectedText = name },
                    modifier = Modifier
                        .fillMaxWidth(.8f),
                    label = { Text("b", fontSize = 18.sp) },
                    textStyle = TextStyle.Default.copy(fontSize = 24.sp, textAlign = TextAlign.Center)
                )
                OutlinedTextField(
                    value = selectedText,
                    singleLine = true,
                    onValueChange = { name -> selectedText = name },
                    modifier = Modifier
                        .fillMaxWidth(.8f),
                    label = { Text("c", fontSize = 18.sp) },
                    textStyle = TextStyle.Default.copy(fontSize = 24.sp, textAlign = TextAlign.Center)
                )
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)) {
                    Text(
                        text = selectedText,
                        modifier = Modifier.padding(start = 8.dp),
                        fontSize = 24.sp
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Column() {
                        Text(
                            text = selectedText,
                            fontSize = 18.sp,
                            textAlign = TextAlign.End,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Text(
                            text = selectedText,
                            fontSize = 12.sp,
                            textAlign = TextAlign.End,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = { /*TODO*/ },
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
}

@Preview(showBackground = true)
@Composable
fun CalculatorsPreview() {
    CarsyComposeTheme {
        CalculatorsScreen()
    }
}