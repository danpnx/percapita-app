package br.com.percapita.android.screens.login.reset_password

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
fun ResetPasswordScreen() {
    MyApplicationTheme() {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(115.dp)) {
                Image(painter = painterResource(id = R.drawable.percapita), contentDescription = "Logotipo",
                    contentScale = ContentScale.Fit)
            }
            Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)) {
                val newPassword = remember { mutableStateOf(TextFieldValue()) }
                val confirmNewPassowrd = remember { mutableStateOf(TextFieldValue())}
                val passwordVisible = remember { mutableStateOf(false) }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Nova senha:",
                    textAlign = TextAlign.Left,
                    modifier = Modifier.fillMaxWidth(0.9f))

                OutlinedTextField(modifier = Modifier.fillMaxWidth(0.9f),
                    value = newPassword.value,
                    onValueChange = { newPassword.value = it },
                    label = { Text(text = "Senha") },
                    visualTransformation = if(passwordVisible.value.not()) PasswordVisualTransformation() else VisualTransformation.None,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    trailingIcon = {
                        val icone = if(passwordVisible.value.not()) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                        val description = if(passwordVisible.value.not()) "Invisivel" else "Visivel"
                        IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                            Icon(imageVector = icone , contentDescription = description)
                        }
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Confirme sua nova senha:",
                    textAlign = TextAlign.Left,
                    modifier = Modifier.fillMaxWidth(0.9f))

                OutlinedTextField(modifier = Modifier.fillMaxWidth(0.9f),
                    value = confirmNewPassowrd.value,
                    onValueChange = { confirmNewPassowrd.value = it },
                    label = { Text(text = "Senha") },
                    visualTransformation = if(passwordVisible.value.not()) PasswordVisualTransformation() else VisualTransformation.None,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    trailingIcon = {
                        val icone = if(passwordVisible.value.not()) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                        val description = if(passwordVisible.value.not()) "Invisivel" else "Visivel"
                        IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                            Icon(imageVector = icone , contentDescription = description)
                        }
                    }
                )

                Spacer(modifier = Modifier.height(32.dp))

                Button(onClick = { /*TODO*/ },
                    modifier = Modifier.fillMaxWidth(0.8f),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF04C457))
                    ){
                    Text(text = "Enviar", fontSize = 16.sp)
                }
            }
        }
    }
}

@Composable
@Preview
fun ResetPasswordScreen_Preview() {
    ResetPasswordScreen()
}
