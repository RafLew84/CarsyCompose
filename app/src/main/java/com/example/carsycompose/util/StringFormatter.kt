package com.example.carsycompose.util

import java.text.DecimalFormat
import java.text.NumberFormat
import java.time.format.DateTimeFormatter
import java.util.*

val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale("pl"))
val decimalFormat = DecimalFormat("###,###.##")
