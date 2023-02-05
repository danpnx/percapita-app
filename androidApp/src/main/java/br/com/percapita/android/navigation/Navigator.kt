package br.com.percapita.android.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.percapita.android.screens.history.HistoryScreen
import br.com.percapita.android.screens.home.HomeScreen
import br.com.percapita.android.screens.login.LoginScreen


/**
 * @project PerCapita
 * @author Daniel Augusto on 02/02/2023
 **/

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigator(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController(),
    initialRoute: Route = Route.HOME_SCREEN,
    isSystemDarkTheme: Boolean
) {
    NavHost(navController = navHostController, startDestination = initialRoute.name) {
        composable(Route.LOGIN.name) {
            LoginScreen(isSystemDarkTheme = isSystemDarkTheme) {
                navHostController.navigate(Route.HOME_SCREEN.name)
            }
        }

        composable(Route.HOME_SCREEN.name) {
            HomeScreen(isSystemDarkTheme = isSystemDarkTheme)
        }

        composable(Route.HISTORY.name) {
            HistoryScreen(isSystemDarkTheme)
        }
    }
}