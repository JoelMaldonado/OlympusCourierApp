package com.atm.olympuscourierapp.util


import android.content.Context
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun Context.show(text: String?) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun getFecha(pattern: String = "yyyy-MM-dd"): String {
    val hoy = SimpleDateFormat(pattern, Locale.getDefault())
    return hoy.format(Date(System.currentTimeMillis()))
}

fun Date?.format(pattern: String = "dd/MM/yyyy"): String {
    if (this == null) return "Sin Formato"
    val outputFormat = SimpleDateFormat(pattern, Locale.getDefault())
        .apply {
            timeZone = TimeZone.getTimeZone("UTC")
        }
    return outputFormat.format(this)
}

