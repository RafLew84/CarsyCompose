package com.example.carsycompose.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.CarRepair
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.LocalGasStation
import androidx.compose.material.icons.filled.LocalParking
import androidx.compose.ui.graphics.vector.ImageVector

enum class CostType(val costType: String, val icon: ImageVector) {
    REFUELING("Tankowanie", Icons.Default.LocalGasStation),
    SERVICE("Serwis", Icons.Default.CarRepair),
    PARKING("Parking", Icons.Default.LocalParking),
    INSURANCE("Ubezpieczenie", Icons.Default.AttachMoney),
    TICKET("Mandat", Icons.Default.Error)
}