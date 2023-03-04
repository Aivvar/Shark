package com.degmagames.shark.utils


import androidx.core.text.isDigitsOnly
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

fun String.isValuePossible(range: IntRange): Boolean {
    return this.isNotEmpty()&&this.isDigitsOnly()&&this.toLong() in range
}
fun String.moneyFormat(): String{
    val dec = DecimalFormat("###,###,###,###,###.00", DecimalFormatSymbols(Locale.ENGLISH))
    var formattedNumber = dec.format(this.toFloat()).replace(",", " ").replace('.',',')
    if(formattedNumber.first()==',')
        formattedNumber="0$formattedNumber"
    return "$$formattedNumber"

}