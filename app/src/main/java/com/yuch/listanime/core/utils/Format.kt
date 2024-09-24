package com.yuch.listanime.core.utils

import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Locale

fun formatNumberWithLocale(number: Int?): String {
    val locale = Locale.getDefault()

    val numberFormat = NumberFormat.getInstance(locale)

    return numberFormat.format(number)
}

fun formatDate(dateString: String?): String {
    return if (dateString.isNullOrEmpty()) {
        "N/A"
    } else {
        try {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.getDefault())
            val outputFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
            val date = inputFormat.parse(dateString)
            outputFormat.format(date!!)
        } catch (e: Exception) {
            "N/A"
        }
    }
}