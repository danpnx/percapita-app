package br.com.percapita.android.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.percapita.android.MyApplicationTheme

/**
 * @project PerCapita
 * @author Daniel Augusto on 02/02/2023
 **/

@Composable
fun BottomBar(darkTheme: Boolean) {
    BottomAppBar(
        contentPadding = PaddingValues(horizontal = 20.dp),
        containerColor = if (darkTheme) Color(0xFF383535) else Color(0xFFF7FAF8),
        contentColor = if (darkTheme) Color.White else Color.Black,
        modifier = Modifier.height(50.dp)
    ) {
        IconButton(onClick = {  }) {
            Icon(Icons.Filled.Home, "Página Inicial", modifier = Modifier.size(27.dp))
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = {  }) {
            Icon(
                Icons.Filled.History,
                "Histórico de Transações", modifier = Modifier.size(27.dp)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = {  }) {
            Icon(Icons.Filled.PieChart, "Relatórios", modifier = Modifier.size(27.dp))
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = {  }) {
            Icon(Icons.Filled.Person, "Perfil", modifier = Modifier.size(27.dp))
        }
    }
}

@Preview(name = "Bottom Bar Preview - Light", showBackground = true)
@Composable
fun BottomBarPreview() {
    MyApplicationTheme() {
        BottomBar(false)
    }
}

@Preview(name = "Bottom Bar Preview - Dark", showBackground = true)
@Composable
fun BottomBarPreviewDark() {
    MyApplicationTheme(darkTheme = true) {
        BottomBar(true)
    }
}