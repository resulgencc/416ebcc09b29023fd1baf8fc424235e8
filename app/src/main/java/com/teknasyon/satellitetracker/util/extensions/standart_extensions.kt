package com.teknasyon.satellitetracker.util.extensions

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.SimpleDateFormat
import java.util.*

fun String.convertTo(
    currentPattern: String = "yyyy-MM-dd",
    newPattern: String = "dd.MM.yyy"
): String =
    try {
        val date = SimpleDateFormat(currentPattern, Locale.getDefault()).parse(this)
        val format = SimpleDateFormat(newPattern, Locale.getDefault())
        format.format(date!!)
    } catch (ex: Exception) {
        ex.printStackTrace()
        this
    }

fun Int.showWithSeparatorDots(): String {
    val locale = Locale.getDefault()
    val symbols = DecimalFormatSymbols(locale).apply {
        groupingSeparator = '.'
    }

    val formatter = DecimalFormat("#,###", symbols)
    return formatter.format(this)
}

fun <T> MutableList<T>.changeAll(other: Collection<T>){
    clear()
    addAll(other)
}