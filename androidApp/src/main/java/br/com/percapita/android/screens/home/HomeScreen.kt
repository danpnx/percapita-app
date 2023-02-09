package br.com.percapita.android.screens.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import br.com.percapita.android.MyApplicationTheme
import br.com.percapita.android.components.BottomBar

/**
 * @project PerCapita
 * @author Daniel Augusto on 02/02/2023
 **/

@Composable
fun HomeScreen(isSystemDarkTheme: Boolean, navController: NavController) {
    MyApplicationTheme(darkTheme = isSystemDarkTheme) {
        Scaffold(
            bottomBar = { BottomBar(navController) },
            scaffoldState = rememberScaffoldState()
        ) {
            LazyColumn(modifier = Modifier.padding(it)) {

            }
        }
    }
}