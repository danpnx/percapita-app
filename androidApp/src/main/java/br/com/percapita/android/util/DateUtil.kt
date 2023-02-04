package br.com.percapita.android.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.DateFormatSymbols
import java.time.format.DateTimeFormatter
import java.util.*

object DateUtil {
    @RequiresApi(Build.VERSION_CODES.O)
    val dtf: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val locale = Locale("pt", "BR")
}