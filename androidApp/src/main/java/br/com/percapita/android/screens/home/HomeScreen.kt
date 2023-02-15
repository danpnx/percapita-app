package br.com.percapita.android.screens.home


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.percapita.android.MyApplicationTheme
import br.com.percapita.android.components.BottomBar
import br.com.percapita.android.components.TransactionCard
import br.com.percapita.android.screens.profile.ProfileScreen
import br.com.percapita.android.util.Lists.transactionList
import java.nio.file.WatchEvent

@Composable
fun HomeScreen(isSystemDarkTheme: Boolean, navController: NavController) {
    MyApplicationTheme(darkTheme = isSystemDarkTheme) {
        Scaffold(
            bottomBar = { BottomBar(navController) },
            scaffoldState = rememberScaffoldState()
        )
        {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 50.dp)

            ) {
                Text(
                    text = "Olá, Leonardo",
                    fontSize = 30.sp,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth(1.3f)
                )

            }
            LazyColumn(
                modifier = Modifier
                    .padding(it)
                    .padding(horizontal = 10.dp),

                ) {
                item {
                    Card(
                        shape = CardDefaults.elevatedShape,
                        elevation = CardDefaults.cardElevation(10.dp),
                        modifier = Modifier
                            .padding(top = 120.dp)
                            .height(200.dp)
                            .fillMaxWidth(),
                        //colors = CardDefaults.cardColors(Green)

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
                    }
                    Card(
                        shape = CardDefaults.elevatedShape,
                        elevation = CardDefaults.cardElevation(10.dp),
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .height(200.dp)
                            .fillMaxWidth(),
                        //colors = CardDefaults.cardColors(Red)
                    ) {
                        Text(
                            text = "Despesas",
                            Modifier.padding(start = 10.dp, top = 5.dp),
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                        Divider(
                            thickness = (2.dp),
                        )
                    }
                    Card(
                        shape = CardDefaults.elevatedShape,
                        elevation = CardDefaults.cardElevation(10.dp),
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .height(200.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Últimos Registros",
                            Modifier.padding(start = 5.dp, top = 5.dp),
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                        Divider(
                            thickness = (2.dp),
                        )
                    }
                }
            }
        }
    }
}


@Composable
@Preview
fun HomeScreen_Preview() {
    HomeScreen(
        false,
        navController = rememberNavController()
    )
}

@Composable
@Preview
fun HomeScreen_PreviewDark() {
    HomeScreen(
        true,
        navController = rememberNavController()
    )
}





