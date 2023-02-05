package br.com.percapita.android.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.percapita.android.MyApplicationTheme

@Composable
fun TopBar(title: String) {
    MyApplicationTheme {
        TopAppBar(
            title = {Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )},
            navigationIcon = {
                IconButton(onClick = { }) {
                    Icon(imageVector = Icons.Filled.ArrowBackIosNew, contentDescription = "Voltar")
                }
            },
            backgroundColor = MaterialTheme.colors.onSurface,
            modifier = Modifier.height(80.dp),
            actions = {
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(Icons.Filled.Menu, contentDescription = "Mini Menu")
                }
            }
        )
    }
}

@Composable
@Preview
fun TopBar_Preview() {
    TopBar("Teste")
}