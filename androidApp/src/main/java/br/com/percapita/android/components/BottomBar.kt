package br.com.percapita.android.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.com.percapita.android.MyApplicationTheme
import br.com.percapita.android.util.Lists.bottomNavList

/**
 * @project PerCapita
 * @author Daniel Augusto on 02/02/2023
 **/

@Composable
fun BottomBar(navController: NavController) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    NavigationBar(
        containerColor = MaterialTheme.colors.surface
    ) {

        bottomNavList.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = (currentRoute == item.route.name),
                onClick = { navController.navigate(item.route.name) },
                icon = { Icon(imageVector = item.icon, contentDescription = item.description) },
                label = { Text(text = item.name, color = MaterialTheme.colors.onSurface) }
            )
        }
    }
}

@Preview(name = "Bottom Bar Preview - Light", showBackground = true)
@Composable
fun BottomBarPreview() {
    MyApplicationTheme(false) {
        BottomBar(rememberNavController())
    }
}

@Preview(name = "Bottom Bar Preview - Dark", showBackground = true)
@Composable
fun BottomBarPreviewDark() {
    MyApplicationTheme(darkTheme = true) {
        BottomBar(rememberNavController())
    }
}