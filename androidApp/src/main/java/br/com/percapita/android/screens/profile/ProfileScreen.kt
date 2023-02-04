package br.com.percapita.android.screens.profile

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
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.percapita.android.MyApplicationTheme
import br.com.percapita.android.R


@Composable
fun ProfileScreen(isSystemDarkTheme: Boolean) {
    MyApplicationTheme() {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 50.dp)) {
                Image(painter = painterResource(id = R.drawable.percapita), contentDescription = "Logotipo",
                    contentScale = ContentScale.Fit)
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp, vertical = 130.dp)) {
                val userName = remember { mutableStateOf(TextFieldValue()) }
                val userEmail = remember { mutableStateOf(TextFieldValue()) }
                val confirmNewPassowrd = remember { mutableStateOf(TextFieldValue())}
                val oldPassword = remember { mutableStateOf(TextFieldValue()) }
                val newPassword = remember { mutableStateOf(TextFieldValue()) }
                val passwordVisible = remember { mutableStateOf(false) }

                Text(
                    text = "Editar Perfil",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth(0.9f))

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Nome Completo:",
                    textAlign = TextAlign.Left,
                    modifier = Modifier.fillMaxWidth(0.9f))
                OutlinedTextField(modifier = Modifier.fillMaxWidth(0.9f),
                    value = userName.value,
                    onValueChange = { userName.value = it },
                    label = { Text(text = "Nome Completo") },
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "E-mail:",
                    textAlign = TextAlign.Left,
                    modifier = Modifier.fillMaxWidth(0.9f))
                TextField(modifier = Modifier.fillMaxWidth(0.9f),
                    value = userEmail.value,
                    onValueChange = { userEmail.value = it },
                    label = { Text(text = "E-mail") },
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Senha atual:",
                    textAlign = TextAlign.Left,
                    modifier = Modifier.fillMaxWidth(0.9f))
                OutlinedTextField(modifier = Modifier.fillMaxWidth(0.9f),
                    value = oldPassword.value,
                    onValueChange = { oldPassword.value = it },
                    label = { Text(text = "Senha atual") },
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
                    text = "Nova senha:",
                    textAlign = TextAlign.Left,
                    modifier = Modifier.fillMaxWidth(0.9f))
                OutlinedTextField(modifier = Modifier.fillMaxWidth(0.9f),
                    value = newPassword.value,
                    onValueChange = { newPassword.value = it },
                    label = { Text(text = "Nova Senha") },
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
                    text = "Confirmação da senha:",
                    textAlign = TextAlign.Left,
                    modifier = Modifier.fillMaxWidth(0.9f))
                OutlinedTextField(modifier = Modifier.fillMaxWidth(0.9f),
                    value = confirmNewPassowrd.value,
                    onValueChange = { confirmNewPassowrd.value = it },
                    label = { Text(text = "Confirmação da senha") },
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
                    Text(text = "Salvar alterações", fontSize = 16.sp)
                }
            }
        }
    }
}

@Composable
@Preview
fun ProfileScreen_Preview() {
    ProfileScreen(true)
}

@Composable
@Preview
fun ProfileScreen_PreviewDark() {
    ProfileScreen(false)
}
