package br.com.percapita.android.screens.configuration

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brightness4
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.percapita.android.MyApplicationTheme
import br.com.percapita.android.components.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ConfigurationScreen(darkTheme: Boolean) {
    MyApplicationTheme(darkTheme) {

        Scaffold(modifier = Modifier.fillMaxSize(),
            backgroundColor = MaterialTheme.colors.background,
            contentColor = MaterialTheme.colors.onBackground,
            topBar = { TopBar(title = "Editar Perfil") },
            bottomBar = { BottomBar() })  {

            ButtonDarkMode(darkTheme = darkTheme)

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp, vertical = 100.dp)) {

                Text(
                    text = "Nome Completo:",
                    textAlign = TextAlign.Left,
                    modifier = Modifier.fillMaxWidth(0.9f))
                Text(
                    text = "Nome completo",
                    textAlign = TextAlign.Left,
                    modifier = Modifier.fillMaxWidth(0.9f))

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "E-mail:",
                    textAlign = TextAlign.Left,
                    modifier = Modifier.fillMaxWidth(0.9f))
                Text(
                    text = "email@email.com",
                    textAlign = TextAlign.Left,
                    modifier = Modifier.fillMaxWidth(0.9f))


                Spacer(modifier = Modifier.height(32.dp))

                Button(onClick = { /*TODO*/ },
                    modifier = Modifier.fillMaxWidth(0.8f),
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary))
                {
                    Text(text = "Editar perfil", fontSize = 16.sp)
                }
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.weight(1f))

                Button(onClick = { /*TODO*/ },
                    modifier = Modifier.fillMaxWidth(0.8f),
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary)
                ){
                    Text(text = "Sair", fontSize = 16.sp)
                }

            }

        }
    }
}

@Composable
@Preview
fun ConfigurationScreen_Preview() {
    MyApplicationTheme(false){
        ConfigurationScreen(false)
    }
}

@Composable
@Preview
fun ConfigurationScreen_PreviewDark() {
    MyApplicationTheme(true){
        ConfigurationScreen(true)
    }
}
