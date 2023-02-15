package br.com.percapita.android.screens.report

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.percapita.android.MyApplicationTheme
import br.com.percapita.android.components.BottomBar
import br.com.percapita.android.components.TopBar
import br.com.percapita.android.screens.register_transaction.RegisterTransactionScreen
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

                }
            }


            /**val transactions = MutableList(3) {
                RegisterTransactionScreen(
                    )**/

            val slices = listOf(
                PieChartData.Slice(10f, Color.Red),
                PieChartData.Slice(30f, Color.Blue),
                PieChartData.Slice(40f, Color.Magenta),
                PieChartData.Slice(20f, Color.Green)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                PieChart(
                    pieChartData = PieChartData(slices),
                    modifier = Modifier
                        .height(150.dp)
                        .weight(0.5f)
                        .wrapContentWidth(),
                    sliceDrawer = SimpleSliceDrawer(
                        sliceThickness =
                        100f
                    )
                )
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    slices.forEach {
                        LabelItem(color = it.color, name = "Abc (${it.value.toInt()}%)")
                    }
                }
            }
        }
    }
}



@Composable
fun LabelItem(color: Color, name: String, nameColor: Color = Color.Black) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Icon(
            Icons.Filled.Circle,
            contentDescription = name,
            modifier = Modifier.height(10.dp),
            tint = color
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = name, color = nameColor)
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