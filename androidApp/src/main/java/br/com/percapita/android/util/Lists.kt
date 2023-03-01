package br.com.percapita.android.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.ui.graphics.Color
import br.com.percapita.android.models.BottomNavItem
import br.com.percapita.android.navigation.Route
import br.com.percapita.enums.TransactionCategory
import br.com.percapita.model.FinancialTransaction
import br.com.percapita.model.Tag
import com.github.tehras.charts.piechart.PieChartData

object Lists {
    val bottomNavList = listOf(
        BottomNavItem(
            "Início",
            Icons.Filled.Home,
            "Página Inicial",
            Route.HOME_SCREEN
        ),
        BottomNavItem(
            "Histórico",
            Icons.Filled.History,
            "Histórico de Transações",
            Route.HISTORY
        ),
        BottomNavItem(
            "Relatório",
            Icons.Filled.PieChart,
            "Relatórios",
            Route.REPORT_SCREEN
        ),
        BottomNavItem(
            "Perfil",
            Icons.Filled.Person,
            "Perfil",
            Route.PROFILE
        )
    )

    val chartList = listOf<PieChartData.Slice>(
        PieChartData.Slice(14.90f, Color.Green),
        PieChartData.Slice(82.55f, Color.Magenta),
        PieChartData.Slice(100.09f, Color.Blue),
        PieChartData.Slice(32.00f, Color.Red),
        PieChartData.Slice(19.35f, Color.Yellow)
    )
}