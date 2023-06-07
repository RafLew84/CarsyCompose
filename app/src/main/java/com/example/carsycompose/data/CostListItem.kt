package com.example.carsycompose.data

sealed class CostListItem(
    val viewType: Int
) {
    class CostGeneralItem(val cost: Cost) : CostListItem(TYPE_GENERAL)
    class CostMonthItem(val month: String) : CostListItem(TYPE_MONTH)
    class CostYearItem(val year: String) : CostListItem(TYPE_YEAR)

    companion object {
        const val TYPE_YEAR = 0
        const val TYPE_MONTH = 1
        const val TYPE_GENERAL = 2
    }
}