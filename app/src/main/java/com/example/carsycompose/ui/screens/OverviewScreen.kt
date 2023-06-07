package com.example.carsycompose.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.carsycompose.ui.theme.CarsyComposeTheme

@Composable
fun OverviewScreen(){
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Cyan),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Overview Screen",
            fontSize = 40.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    CarsyComposeTheme {
        OverviewScreen()
    }
}