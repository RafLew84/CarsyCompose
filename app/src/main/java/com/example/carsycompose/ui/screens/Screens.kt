package com.example.carsycompose.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screens(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Overview : Screens("overview", "Overview", Icons.Default.Home)
    object TimeLine : Screens("timeline", "Time Line", Icons.Default.Timeline)
    object Calculators : Screens("calculators", "Calculators", Icons.Default.Calculate)
}