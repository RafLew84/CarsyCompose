package com.example.carsycompose.data

import com.example.carsycompose.util.polishMonthsNames
import java.time.LocalDate
import java.time.Month
import java.time.Year
import kotlin.random.Random

object DataProvider {

    val cars = listOf(
        Car("Domowy", "Skoda", "Fabia", 2002, generalCosts(100)),
        Car("Służbowy", "BMW", "Coupe", 2015, generalCosts(200)),
        Car("Kolekcjonerski", "Fiat", "125p", 1985, generalCosts(120)),
        Car("Sportowy", "Lamborghini", "Murcielago", 2012, generalCosts(100)),
        Car("Zapasowy", "Skoda", "Superb", 2010, generalCosts(120)),
        Car("SUV", "Skoda", "Kodiaq", 2020, generalCosts(300))
    )

    private fun generalCosts(size: Int) = List(size) {
        Cost(
            CostType.values()[Random.nextInt(CostType.values().size)],
            LocalDate.of(Random.nextInt(2005, 2023), Random.nextInt(1,13), Random.nextInt(1,28)),
            Random.nextInt(5000)
        )
    }

    fun getTimeLineList(costs: List<Cost>) = costs
            .sortedBy { it.date }
            .groupBy { it.date.year }
            .flatMap { (year, costsOfYear) -> costListItemsByMonths(year, costsOfYear) }

    private fun costListItemsByMonths(
        year: Int,
        costsOfYear: List<Cost>
    ) = listOf(
        CostListItem.CostYearItem(year.toString()),
        *costsOfYear.groupBy { it.date.month }
            .flatMap { (month, costsOfMonth) -> costListItemsByDays(month, costsOfMonth) }
            .toTypedArray()
    )

    private fun costListItemsByDays(
        month: Month,
        costsOfMonth: List<Cost>
    ) = listOf(
        CostListItem.CostMonthItem(polishMonthsNames(month)),
        *costsOfMonth.sortedBy { it.date.dayOfMonth }
            .map { CostListItem.CostGeneralItem(it) }
            .toTypedArray()
    )
}