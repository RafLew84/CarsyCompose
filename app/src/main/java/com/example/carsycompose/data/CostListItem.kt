package com.example.carsycompose.data

sealed class CostListItem() {
    class CostGeneralItem(val cost: Cost) : CostListItem()
    class CostMonthItem(val month: String) : CostListItem()
    class CostYearItem(val year: String) : CostListItem()
}