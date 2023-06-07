package com.example.carsycompose.util

import java.time.Month

fun polishMonthsNames(month: Month): String {
    return when (month) {
        Month.JANUARY -> "STYCZEŃ"
        Month.FEBRUARY -> "LUTY"
        Month.MARCH -> "MARZEC"
        Month.APRIL -> "KWIECIEŃ"
        Month.MAY -> "MAJ"
        Month.JUNE -> "CZERWIEC"
        Month.JULY -> "LIPIEC"
        Month.AUGUST -> "SIERPIEŃ"
        Month.SEPTEMBER -> "WRZESIEŃ"
        Month.OCTOBER -> "PAŹDZIERNIK"
        Month.NOVEMBER -> "LISTOPAD"
        Month.DECEMBER -> "GRUDZIEŃ"
        else -> "unknown"
    }
}