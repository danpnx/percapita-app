package br.com.percapita.android.screens.report

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.percapita.android.MyApplicationTheme
import br.com.percapita.android.components.BottomBar
import br.com.percapita.android.components.TopBar
import br.com.percapita.android.util.Lists.chartList
import com.github.tehras.charts.piechart.PieChart
import com.github.tehras.charts.piechart.PieChartData
import com.github.tehras.charts.piechart.animation.simpleChartAnimation
import com.github.tehras.charts.piechart.renderer.SimpleSliceDrawer

/**
 * @project PerCapita
 * @author Daniel Augusto on 08/02/2023
 **/

@Composable
fun ReportScreen(
    isSystemDarkTheme: Boolean,
    navController: NavController,
    onBack: () -> Unit
) {
    MyApplicationTheme(darkTheme = isSystemDarkTheme) {
        Scaffold(
            scaffoldState = rememberScaffoldState(),
            topBar = { TopBar(title = "Relatório", onBack) },
            bottomBar = { BottomBar(navController = navController) }
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    PieChart(
                        pieChartData = PieChartData(chartList),
                        modifier = Modifier.fillMaxSize(),
                        animation = simpleChartAnimation(),
                        sliceDrawer = SimpleSliceDrawer()
                    )
                    
                    Text(text = "OLÁ")
                }
            }
        }
    }
}

@Preview(name = "Report Screen Preview - Light")
@Composable
fun ReportScreenPreview() {
    ReportScreen(isSystemDarkTheme = false, navController = rememberNavController(), onBack = {})
}

@Preview(name = "Report Screen Preview - Dark")
@Composable
fun ReportScreenPreviewDark() {
    ReportScreen(isSystemDarkTheme = true, navController = rememberNavController(), onBack = {})
}