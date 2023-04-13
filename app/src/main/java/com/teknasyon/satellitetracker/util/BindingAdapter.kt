package com.teknasyon.satellitetracker.util

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.widget.TextView
import androidx.databinding.BindingAdapter


@BindingAdapter(value = ["bind:value", "bind:resourceId"])
fun TextView.setTextAndSpan(value: String?, resourceId: Int) {
    val nonNullValue = value ?: ""
    text = resources.getString(resourceId, nonNullValue)
    val spannable = SpannableString(text)

    val indexOfColon = text.indexOf(":") + 1
    spannable.setSpan(
        StyleSpan(Typeface.BOLD),
        0,
        indexOfColon,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    text = spannable
}