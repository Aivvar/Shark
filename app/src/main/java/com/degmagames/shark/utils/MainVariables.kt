package com.degmagames.shark.utils

import androidx.compose.runtime.mutableStateOf

object MainVariables {
    val pressedItem = mutableStateOf(2)
    val exchangePerMonth = mutableStateOf(1000)
    var creditPercent = mutableStateOf(20)
    var money = mutableStateOf(0)
    val moneyRange = 1..30000
    val termRange = 1..30
    const val minimum = 10000
}