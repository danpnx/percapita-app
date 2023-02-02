package br.com.percapita.android.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.percapita.android.MyApplicationTheme
import br.com.percapita.android.R

@Composable
fun ForgotPasswordScreen() {
    MyApplicationTheme() {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(64.dp)) {
                Image(painter = painterResource(id = R.drawable.forgotpassword), contentDescription = "ForgotPassword",
                contentScale = ContentScale.Fit)
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                val email = remember { mutableStateOf(TextFieldValue()) }
                
                Text(text = "Esqueceu sua senha?", fontSize = 25.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Informe seu e-mail para a recuperação", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                Text(text = "da sua conta", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(40.dp))
                OutlinedTextField(value = email.value, onValueChange = {email.value = it},
                label = { Text(text = "E-mail")},
                modifier = Modifier.fillMaxWidth(0.9f))

                Spacer(modifier = Modifier.height(30.dp))

                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF04C457)),
                    modifier = Modifier.fillMaxWidth(0.7f)
                ) {
                    Text(text = "Enviar")
                }
            }
        }
    }
}

@Composable
@Preview
fun ForgotPasswordScreen_Preview() {
    ForgotPasswordScreen()
}