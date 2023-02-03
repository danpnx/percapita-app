package br.com.percapita.android.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.percapita.android.screens.home.HomeScreen
import br.com.percapita.android.screens.login.LoginScreen


/**
 * @project PerCapita
 * @author Daniel Augusto on 02/02/2023
 **/

@Composable
fun Navigator(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController(),
    initialRoute: Route = Route.LOGIN,
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
    }
}