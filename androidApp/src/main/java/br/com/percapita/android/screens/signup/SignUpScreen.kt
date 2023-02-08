package br.com.percapita.android.screens.signup

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
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
fun SignUpScreen(onLoginNavigation: () -> Unit) {
    MyApplicationTheme(darkTheme = false) {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 30.dp, vertical = 60.dp)) {
                Image(painter = painterResource(id = R.drawable.percapita), contentDescription = "Logotipo",
                    contentScale = ContentScale.Fit)
                Spacer(modifier = Modifier.height(50.dp))
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(16.dp)
            ) {
                val name = remember { mutableStateOf(TextFieldValue()) }
                val email = remember { mutableStateOf(TextFieldValue()) }
                val password = remember { mutableStateOf(TextFieldValue()) }
                val confirmPassword = remember { mutableStateOf(TextFieldValue()) }
                val passwordVisible = remember { mutableStateOf(false) }
                val context = LocalContext.current
                
                Text(
                    text = "Pronto para colocar sua renda em ordem?", fontSize = 19.sp,
                    fontWeight = FontWeight.Light
                )
                Text(
                    text = "Preencha os dados abaixo e crie sua conta!", fontSize = 19.sp,
                    fontWeight = FontWeight.Light
                )
                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    value = name.value, onValueChange = { name.value = it },
                    label = { Text(text = "Nome") }, modifier = Modifier.fillMaxWidth(0.9f),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )
                Spacer(modifier = Modifier.height(15.dp))

                OutlinedTextField(
                    value = email.value, onValueChange = { email.value = it },
                    label = { Text(text = "E-mail") }, modifier = Modifier.fillMaxWidth(0.9f),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )
                Spacer(modifier = Modifier.height(15.dp))

                OutlinedTextField(value = password.value, onValueChange = { password.value = it },
                    label = { Text(text = "Senha") }, modifier = Modifier.fillMaxWidth(0.9f),
                    singleLine = true,
                    visualTransformation = if (passwordVisible.value.not()) PasswordVisualTransformation() else VisualTransformation.None,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    trailingIcon = {
                        val icone = if (passwordVisible.value.not()) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                        val description = if (passwordVisible.value.not()) "Invisivel" else "Visivel"
                        IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                            Icon(imageVector = icone, contentDescription = description)
                        }
                    })
                Spacer(modifier = Modifier.height(15.dp))

                OutlinedTextField(value = confirmPassword.value,
                    onValueChange = { confirmPassword.value = it },
                    label = { Text(text = "Confirme sua senha") },
                    modifier = Modifier.fillMaxWidth(0.9f),
                    visualTransformation = if (passwordVisible.value.not()) PasswordVisualTransformation() else VisualTransformation.None,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    trailingIcon = {
                        val icone =
                            if (passwordVisible.value.not()) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                        val description =
                            if (passwordVisible.value.not()) "Invisivel" else "Visivel"
                        IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                            Icon(imageVector = icone, contentDescription = description)
                        }
                    })
                Spacer(modifier = Modifier.height(30.dp))

                Button(
                    onClick = { onLoginNavigation.invoke()
                                Toast.makeText(context, "Conta criada com sucesso", Toast.LENGTH_SHORT).show()
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF04C457)),
                    modifier = Modifier.fillMaxWidth(0.8f)
                ) {
                    Text(text = "Criar conta")
                }
            }
        }
    }
}

@Composable
@Preview
fun SignUpScreen_Preview() {
    SignUpScreen({})
}