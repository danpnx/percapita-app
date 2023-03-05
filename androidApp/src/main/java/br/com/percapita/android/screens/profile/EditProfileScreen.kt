package br.com.percapita.android.screens.profile

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.percapita.android.MyApplicationTheme
import br.com.percapita.android.components.TopBar
import br.com.percapita.model.User
import br.com.percapita.utils.DataResult


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ConfigurationScreen(darkTheme: Boolean, onBack: () -> Unit) {
    MyApplicationTheme(darkTheme) {
        val viewModel: EditProfileViewModel = viewModel()
        val profile by viewModel.profile.collectAsState()
        Scaffold(modifier = Modifier.fillMaxSize(),
                backgroundColor = MaterialTheme.colors.background,
                contentColor = MaterialTheme.colors.onBackground,
                topBar = {TopBar(title = "Editar Perfil", onBack)},
                ) {

            when(profile) {
                is DataResult.Success -> ConfigurationScreenContent(profile as DataResult.Success<User>)
                else -> Unit
            }

        }
    }
}

@Composable
fun ConfigurationScreenContent(result: DataResult.Success<User>) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp, vertical = 40.dp)
            .fillMaxSize()) {
        val confirmNewPassowrd = remember { mutableStateOf(TextFieldValue())}
        val oldPassword = remember { mutableStateOf(TextFieldValue()) }
        val newPassword = remember { mutableStateOf(TextFieldValue()) }
        val passwordVisible = remember { mutableStateOf(false) }
        val profile = result.data
        val viewModel = viewModel<EditProfileViewModel>()

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Nome Completo:",
            textAlign = TextAlign.Left,
            modifier = Modifier.fillMaxWidth(0.9f))
        OutlinedTextField(modifier = Modifier.fillMaxWidth(0.9f),
            value = profile.name,
            onValueChange = { profile.name = it },
            label = { Text(text = "Nome Completo") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = MaterialTheme.colors.onBackground,
                unfocusedLabelColor  = MaterialTheme.colors.onSurface
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "E-mail:",
            textAlign = TextAlign.Left,
            modifier = Modifier.fillMaxWidth(0.9f))
        TextField(modifier = Modifier.fillMaxWidth(0.9f),
            value = profile.username,
            onValueChange = { profile.username },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = MaterialTheme.colors.onBackground,
                unfocusedLabelColor  = MaterialTheme.colors.onBackground
            )
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
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = MaterialTheme.colors.onBackground,
                unfocusedLabelColor  = MaterialTheme.colors.onSurface
            ),
            visualTransformation = if(passwordVisible.value.not()) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val icone = if(passwordVisible.value.not()) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                val description = if(passwordVisible.value.not()) "Invisivel" else "Visivel"
                IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                    Icon(imageVector = icone , contentDescription = description,
                        tint = MaterialTheme.colors.onSurface)
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
            onValueChange = { profile.password },
            label = { Text(text = "Nova Senha") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = MaterialTheme.colors.onBackground,
                unfocusedLabelColor  = MaterialTheme.colors.onSurface
            ),
            visualTransformation = if(passwordVisible.value.not()) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val icone = if(passwordVisible.value.not()) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                val description = if(passwordVisible.value.not()) "Invisivel" else "Visivel"
                IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                    Icon(imageVector = icone , contentDescription = description,
                        tint = MaterialTheme.colors.onSurface)
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
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = MaterialTheme.colors.onBackground,
                unfocusedLabelColor  = MaterialTheme.colors.onSurface
            ),
            visualTransformation = if(passwordVisible.value.not()) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val icone = if(passwordVisible.value.not()) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                val description = if(passwordVisible.value.not()) "Invisivel" else "Visivel"
                IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                    Icon(imageVector = icone , contentDescription = description,
                        tint = MaterialTheme.colors.onSurface)
                }
            }
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = {
            if (oldPassword.value.text == profile.password && newPassword == confirmNewPassowrd) {
                viewModel.editProfile(profile)
            }

            else error("Senhas não correspondem")},
            modifier = Modifier.fillMaxWidth(0.8f),
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary)
        ){
            Text(text = "Salvar alterações", fontSize = 16.sp)
        }
    }
}

@Composable
@Preview
fun EditProfileScreen_Preview() {
    ConfigurationScreen(darkTheme = false, onBack = {})
}

@Composable
@Preview
fun EditProfileScreen_PreviewDark() {
    ConfigurationScreen(darkTheme = true, onBack = {})
}
