package com.degmagames.shark.utils


import androidx.core.text.isDigitsOnly

fun String.isValuePossible(range: IntRange): Boolean {
    return this.isNotEmpty()&&this.isDigitsOnly()&&this.toInt() in range
}