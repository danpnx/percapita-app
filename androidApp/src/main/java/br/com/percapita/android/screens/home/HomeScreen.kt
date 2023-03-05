package br.com.percapita.android.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.percapita.android.MyApplicationTheme
import br.com.percapita.android.components.BottomBar
import br.com.percapita.model.Report
import br.com.percapita.model.User
import br.com.percapita.utils.DataResult
import com.github.tehras.charts.piechart.PieChart
import com.github.tehras.charts.piechart.PieChartData
import com.github.tehras.charts.piechart.renderer.SimpleSliceDrawer

@Composable
fun HomeScreen(isSystemDarkTheme: Boolean, navController: NavController) {
    MyApplicationTheme(darkTheme = isSystemDarkTheme) {
        Scaffold(
            bottomBar = { BottomBar(navController) },
            scaffoldState = rememberScaffoldState()
        ) {
            val viewModel = viewModel<HomeViewModel>()
            val reportState by viewModel.report.collectAsState()

            Column(
                modifier = Modifier
                    .padding(it)
                    .padding(horizontal = 10.dp),
            ) {

                when(reportState) {
                    is DataResult.Success -> ContentHome(
                        reportState as DataResult.Success<Report>
                    )
                    else -> ContentHomeScreenEmpty()
                }
            }
        }
    }
}

@Composable
fun ContentHome(result: DataResult.Success<Report>) {
    val report = result.data
    val summaryData = report.summaryData
    val chartData = report.chartData

    summaryData.forEach {
        Card(
            shape = CardDefaults.elevatedShape,
            elevation = CardDefaults.cardElevation(1.dp),
            modifier = Modifier
                .padding(top = 10.dp)
                .height(120.dp)
                .fillMaxWidth(),
        ) {
            Text(
                text = it.key,
                Modifier.padding(start = 10.dp, top = 5.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
            )
            Divider(
                thickness = (2.dp),
            )
            Text(
                text = "R$ ${it.value}",
                color = colorNumber(it.value),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 10.dp, top = 5.dp)
            )
        }
    }
}

fun colorNumber(value: Double): Color {
    return when {
        value < 0 -> Color(0xFFFB6969)
        value == 0.0 -> Color(0xFF000000)
        else -> Color(0xFF00AB41)
    }
}

@Composable
fun ContentHomeScreenEmpty() {

    Card(
        shape = CardDefaults.elevatedShape,
        elevation = CardDefaults.cardElevation(1.dp),
        modifier = Modifier
            .padding(top = 10.dp)
            .height(120.dp)
            .fillMaxWidth(),
    ) {
        Text(
            text = "Saldo",
            Modifier.padding(start = 10.dp, top = 5.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
        )
        Divider(
            thickness = (2.dp),
        )
        Text(
            text = "R$ 0.0",
            color = Color.Black,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 10.dp, top = 5.dp)
        )
    }

    Card(
        shape = CardDefaults.elevatedShape,
        elevation = CardDefaults.cardElevation(1.dp),
        modifier = Modifier
            .padding(top = 10.dp)
            .height(120.dp)
            .fillMaxWidth(),
    ) {
        Text(
            text = "Receitas",
            Modifier.padding(start = 10.dp, top = 5.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
        )
        Divider(
            thickness = (2.dp),
        )
        Text(
            text = "R$ 0.0",
            color = Color.Black,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 10.dp, top = 5.dp)
        )
    }

    Card(
        shape = CardDefaults.elevatedShape,
        elevation = CardDefaults.cardElevation(1.dp),
        modifier = Modifier
            .padding(top = 10.dp)
            .height(120.dp)
            .fillMaxWidth(),
    ) {
        Text(
            text = "Despesas",
            Modifier.padding(start = 10.dp, top = 5.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
        )
        Divider(
            thickness = (2.dp),
        )
        Text(
            text = "R$ 0.0",
            color = Color.Black,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 10.dp, top = 5.dp)
        )
    }
}

//@Composable
//fun LabelItem(color: Color, name: String, nameColor: Color = Color.Black) {
//    Row(
//        verticalAlignment = Alignment.CenterVertically
//    )
//    {
//        androidx.compose.material.Icon(
//            Icons.Filled.Circle,
//            contentDescription = name,
//            modifier = Modifier.height(10.dp),
//            tint = color
//        )
//        Spacer(modifier = Modifier.width(8.dp))
//        Text(text = name, color = nameColor)
//    }
//}

@Composable
@Preview
fun HomeScreen_Preview() {
    HomeScreen(
        isSystemDarkTheme = false,
        navController = rememberNavController()
    )
}

@Composable
@Preview
fun HomeScreen_PreviewDark() {
    HomeScreen(
        isSystemDarkTheme = true,
        navController = rememberNavController()
    )
}





