package br.com.percapita.android.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.DateFormatSymbols
import java.time.format.DateTimeFormatter
import java.util.*

object DateUtil {
    @RequiresApi(Build.VERSION_CODES.O)
    val dtf: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
    val locale = Locale("pt", "BR")
    const val DATE_MASK = "##/##/####"
    const val DATE_LENGTH = 8
}