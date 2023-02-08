package br.com.percapita.android.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.percapita.android.MyApplicationTheme
import br.com.percapita.android.R

@Composable
fun LoginScreen(
    isSystemDarkTheme: Boolean,
    onHomeNavigation: () -> Unit,
    onSignUpNavigation: () -> Unit,
    onForgotPasswordNavigation: () -> Unit
) {
    MyApplicationTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(16.dp)
            ) {
                Image(painter = painterResource(id = R.drawable.percapita), contentDescription = "Logotipo",
                    contentScale = ContentScale.Fit, modifier = Modifier.size(200.dp))
                Text(
                    text = "Bem vindo",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 26.sp)
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Realize seu login ou crie uma conta com a gente!",
                    fontWeight = FontWeight.Light, color = Color.Black, fontSize = 15.sp)
                Spacer(modifier = Modifier.height(400.dp))
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)) {
            val login = remember { mutableStateOf(TextFieldValue()) }
            val password = remember { mutableStateOf(TextFieldValue()) }
            val passwordVisible = remember { mutableStateOf(false) }

            Spacer(modifier = Modifier.height(350.dp))
            OutlinedTextField(modifier = Modifier.fillMaxWidth(), value = login.value,
                onValueChange = { login.value = it }, label = { Text(text = "E-mail")})

            OutlinedTextField(modifier = Modifier.fillMaxWidth(), value = password.value,
                onValueChange = { password.value = it }, label = { Text(text = "Senha") },
                visualTransformation = if(passwordVisible.value.not()) PasswordVisualTransformation() else VisualTransformation.None,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val icone = if(passwordVisible.value.not()) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    val description = if(passwordVisible.value.not()) "Invisivel" else "Visivel"
                    IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                        Icon(imageVector = icone , contentDescription = description)
                    }
                } )

            TextButton(onClick = { onForgotPasswordNavigation.invoke() }) {
                Text(text = "Esqueci minha senha", modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End, color = Color.Black)
            }

            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { onHomeNavigation.invoke() },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF04C457))) {
                Text(text = "Entrar", fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(20.dp))
            TextButton(onClick = { onSignUpNavigation.invoke() }) {
                Text(text = "Criar conta", modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center, fontSize = 16.sp, color = Color.Black)
            }
        }
    }
}


@Composable
@Preview
fun LoginScreen_Preview() {
    LoginScreen(isSystemDarkTheme = false, {}, {}) {}
}