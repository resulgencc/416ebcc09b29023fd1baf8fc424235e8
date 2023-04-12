package com.teknasyon.satellitetracker.util.extensions

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
