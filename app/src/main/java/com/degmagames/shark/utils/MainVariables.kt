package com.degmagames.shark.utils

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import com.degmagames.shark.repository.SharedPref
import com.degmagames.shark.utils.Constants.CREDIT_PERCENT
import com.degmagames.shark.utils.Constants.CREDIT_PERCENT_TIME
import com.degmagames.shark.utils.Constants.FIRST_OPEN
import com.degmagames.shark.utils.Constants.MONEY_PER_CLICK
import com.degmagames.shark.utils.Constants.MONEY_TOTAL
import java.util.*
import kotlin.random.Random.Default.nextInt

object MainVariables {
    val pressedItem = mutableStateOf(2)
    val exchangePerMonth = mutableStateOf(1000)
    var creditPercent = mutableStateOf(0)
    var lastCreditTime = 0L
    var money = mutableStateOf(0.00f)
    var moneyPerClick = mutableStateOf(1.00f)
    val moneyRange = 1..30000
    val termRange = 1..30
    const val minimum = 10000

    //InvestScreen
    var profitTotal = -100000000.00f
    var percentTotal = 3.42f
    var dividend = -100000000.00f
    //
    val sharedPref = SharedPref()
    fun initialization(context: Context) {
        val firstOpen = sharedPref.getBoolean(FIRST_OPEN,context)
        if(firstOpen)
        {
            lastCreditTime = Date().time
            creditPercent.value = nextInt(8,30)
            sharedPref.saveInt(CREDIT_PERCENT, creditPercent.value,context)
            sharedPref.saveBoolean(FIRST_OPEN, false,context)
            sharedPref.saveLong(CREDIT_PERCENT_TIME, lastCreditTime,context)
            sharedPref.saveFloat(MONEY_TOTAL,0.00f,context)
            sharedPref.saveFloat(MONEY_PER_CLICK,1.00f,context)
            return
        }
        moneyPerClick.value = sharedPref.getFloat(MONEY_PER_CLICK,context)
        money.value = sharedPref.getFloat(MONEY_TOTAL,context)
        creditPercent.value = sharedPref.getInt(CREDIT_PERCENT,context)
        lastCreditTime = sharedPref.getLong(CREDIT_PERCENT_TIME,context)
        if(Date().time-lastCreditTime>3600000)
        {
            lastCreditTime = Date().time
            val newPercent = nextInt(8,30)
            creditPercent.value = newPercent
            sharedPref.saveLong(CREDIT_PERCENT_TIME, lastCreditTime,context)
            sharedPref.saveInt(CREDIT_PERCENT,newPercent,context)
        }
    }

    fun moneyAddition(addend: Float,context: Context) {
        money.value=money.value+addend
        sharedPref.saveFloat(MONEY_TOTAL,money.value, context)
    }
}