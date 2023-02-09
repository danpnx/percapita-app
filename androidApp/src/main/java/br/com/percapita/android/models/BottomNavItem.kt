package br.com.percapita.android.models

import androidx.compose.ui.graphics.vector.ImageVector
import br.com.percapita.android.navigation.Route

data class BottomNavItem(
    val name: String,
    val icon: ImageVector,
    val description: String,
    val route: Route
)
