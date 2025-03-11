package com.atm.olympuscourierapp.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


fun String.toDate(): Date? {
    return try {
        val pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
        val format = SimpleDateFormat(pattern, Locale.getDefault())
        format.parse(this)
    } catch (e: Exception) {
        null
    }
}

fun String.capitalizeWords(): String {
    return this.split(" ").joinToString(" ") { word ->
        word.lowercase().replaceFirstChar { it.uppercase() }
    }
}