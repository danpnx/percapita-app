package br.com.percapita.android.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.percapita.android.screens.history.HistoryScreen
import br.com.percapita.android.screens.home.HomeScreen
import br.com.percapita.android.screens.login.LoginScreen
import br.com.percapita.android.screens.login.forgot_password.ForgotPasswordScreen
import br.com.percapita.android.screens.profile.ConfigurationScreen
import br.com.percapita.android.screens.profile.ProfileScreen
import br.com.percapita.android.screens.register_transaction.RegisterTransactionScreen
import br.com.percapita.android.screens.report.ReportScreen
import br.com.percapita.android.screens.signup.SignUpScreen
import br.com.percapita.android.screens.tag.CreateTagScreen
import br.com.percapita.android.screens.tag.TagScreen


/**
 * @project PerCapita
 * @author Daniel Augusto on 02/02/2023
 **/

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigator(
    navHostController: NavHostController = rememberNavController(),
    initialRoute: Route = Route.LOGIN,
    isSystemDarkTheme: Boolean
) {

    NavHost(navController = navHostController, startDestination = initialRoute.name) {

        composable(Route.LOGIN.name) {
            LoginScreen(isSystemDarkTheme = isSystemDarkTheme,
                onHomeNavigation = { navHostController.navigate(Route.HOME_SCREEN.name) },
                onSignUpNavigation = { navHostController.navigate(Route.SIGNUP.name) },
                onForgotPasswordNavigation = { navHostController.navigate(Route.FORGOT_PASSWORD.name) })
        }

        composable(Route.HOME_SCREEN.name) {
            HomeScreen(isSystemDarkTheme = false, navController = navHostController)
        }

        composable(Route.SIGNUP.name) {
            SignUpScreen(onLoginNavigation = { navHostController.navigate(Route.LOGIN.name) })
        }

        composable(Route.FORGOT_PASSWORD.name) {
            ForgotPasswordScreen(onLoginScreen = { navHostController.navigate(Route.LOGIN.name) })
        }

        composable(Route.HISTORY.name) {
            HistoryScreen(
                isSystemDarkTheme = isSystemDarkTheme,
                navController = navHostController,
                onBack = { navHostController.popBackStack() },
                onClickRegisterTransaction = { navHostController.navigate(Route.REGISTER_TRANSACTION.name) }
            )
        }

        composable(Route.REGISTER_TRANSACTION.name) {
            RegisterTransactionScreen(
                isSystemDarkTheme = isSystemDarkTheme,
                onBack = { navHostController.popBackStack() },
                onOpenTags = { navHostController.navigate(Route.TAG_SCREEN.name) },
                onConfirm = {}
            )
        }

        composable(Route.TAG_SCREEN.name) {
            TagScreen(
                isSystemDarkTheme = isSystemDarkTheme,
                onBack = { navHostController.popBackStack() },
                onCreateTag = { navHostController.navigate(Route.CREATE_TAG_SCREEN.name) }
            )
        }

        composable(Route.CREATE_TAG_SCREEN.name) {
            CreateTagScreen(isSystemDarkTheme = isSystemDarkTheme,
                onBack = { navHostController.popBackStack() },
                onTagScreenNavigation = { navHostController.navigate(Route.TAG_SCREEN.name)})
        }

        composable(Route.REPORT_SCREEN.name) {
            ReportScreen(
                isSystemDarkTheme = isSystemDarkTheme,
                navController = navHostController,
                onBack = { navHostController.popBackStack() }
            )
        }

        composable(Route.PROFILE.name) {
            ProfileScreen(
                darkTheme = isSystemDarkTheme,
                navController = navHostController,
                onBack = { navHostController.popBackStack() },
                onEditProfile = { navHostController.navigate(Route.CONFIG_SCREEN.name) }
            )
        }

        composable(Route.CONFIG_SCREEN.name) {
            ConfigurationScreen(
                darkTheme = isSystemDarkTheme,
                onBack = { navHostController.popBackStack() },
                onSaveChanges = {}
            )
        }
    }
}
