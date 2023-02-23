package com.degmagames.shark.utils


import androidx.core.text.isDigitsOnly
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

fun String.isValuePossible(range: IntRange): Boolean {
    return this.isNotEmpty()&&this.isDigitsOnly()&&this.toInt() in range
}
fun String.moneyFormat(): String{
    val dec = DecimalFormat("###,###,###,###,###.00", DecimalFormatSymbols(Locale.ENGLISH))
    val formattedNumber = dec.format(this.toFloat()).replace(",", " ").replace('.',',')
    return formattedNumber

}