package br.com.percapita.android.screens.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.percapita.android.MyApplicationTheme
import br.com.percapita.android.components.BottomBar

@Composable
fun HomeScreen(isSystemDarkTheme: Boolean) {
    MyApplicationTheme(darkTheme = isSystemDarkTheme) {
        Scaffold(
            bottomBar = { BottomBar() },
            scaffoldState = rememberScaffoldState()
        ) {
            LazyColumn(modifier = Modifier.padding(it).padding(horizontal = 10.dp)) {
                item {
                    Card(
                        shape = CardDefaults.elevatedShape,
                        elevation = CardDefaults.cardElevation(1.dp),
                        modifier = Modifier.padding(top = 150.dp).height(100.dp).fillMaxWidth()
                    ) {
                        Text(
                            text = "Receitas",
                            Modifier.padding(start = 10.dp, top = 5.dp)
                        )
                    }
                    Card(
                        shape = CardDefaults.elevatedShape,
                        elevation = CardDefaults.cardElevation(1.dp),
                        modifier = Modifier.padding(top = 20.dp).height(100.dp).fillMaxWidth()
                    ) {
                        Text(
                            text = "Despesas",
                            Modifier.padding(start = 10.dp, top = 5.dp)
                        )
                    }
                    Card(
                        shape = CardDefaults.elevatedShape,
                        elevation = CardDefaults.cardElevation(1.dp),
                        modifier = Modifier.padding(top = 20.dp).height(100.dp).fillMaxWidth()
                    ) {
                        Text(
                            text = "Últimos Registros",
                            Modifier.padding(start = 10.dp, top = 5.dp)
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
    HomeScreen(false)
}


