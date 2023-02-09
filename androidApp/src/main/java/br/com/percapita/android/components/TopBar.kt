package br.com.percapita.android.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import br.com.percapita.android.MyApplicationTheme

@Composable
fun TopBar(title: String, onBack: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier,
                color = MaterialTheme.colors.onBackground
            )
        },
        navigationIcon = {
            IconButton(onClick = onBack ) {
                Icon(imageVector = Icons.Filled.ArrowBackIosNew, contentDescription = "Voltar")
            }
        },
        backgroundColor = MaterialTheme.colors.background
    )
}

@Composable
@Preview
fun TopBar_Preview() {
    MyApplicationTheme(false) {
        TopBar("Teste", onBack = {})
    }
}

@Composable
@Preview
fun TopBarDark_Preview() {
    MyApplicationTheme(true) {
        TopBar("Teste", onBack = {})
    }
}