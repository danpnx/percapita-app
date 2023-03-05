package br.com.percapita.android.screens.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Brightness4
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.percapita.android.MyApplicationTheme
import br.com.percapita.android.components.*
import br.com.percapita.model.User
import br.com.percapita.utils.DataResult

@Composable
fun ProfileTopBar(title: String, darkTheme: Boolean, onBack: () -> Unit) {
    MyApplicationTheme(darkTheme) {
        var isSystemDarkTheme = remember { mutableStateOf(darkTheme) }
        val onChangeTheme = {
            isSystemDarkTheme.value = !isSystemDarkTheme.value
        }
        TopAppBar(
            title = {
                Text(
                    text = title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Left,
                    modifier = Modifier,
                    color = MaterialTheme.colors.onBackground
                )
            },
            navigationIcon = {
                IconButton(onClick = { onBack.invoke() }) {
                    Icon(imageVector = Icons.Filled.ArrowBackIosNew, contentDescription = "Voltar")
                }
            },
            actions ={
                Button(onClick = { onChangeTheme },
                    shape = CircleShape,
                    modifier = Modifier.padding(10.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.surface)) {
                    Icon(
                        Icons.Default.Brightness4,
                        contentDescription = "Dark Mode",
                        modifier = Modifier
                            .height(16.dp)
                            .padding(0.dp),
                        tint = MaterialTheme.colors.onBackground)
                }
            },
            backgroundColor = MaterialTheme.colors.background,
            modifier = Modifier.height(80.dp)
        )
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProfileScreen(
    darkTheme: Boolean,
    navController: NavController,
    onBack: () -> Unit,
    onEditProfile: () -> Unit
) {
    MyApplicationTheme(darkTheme) {

        val viewModel: ProfileViewModel = viewModel()
        val profile by viewModel.profile.collectAsState()

        Scaffold(modifier = Modifier.fillMaxSize(),
            backgroundColor = MaterialTheme.colors.background,
            contentColor = MaterialTheme.colors.onBackground,
            topBar = { ProfileTopBar(title = "Perfil", darkTheme, onBack) },
            bottomBar = { BottomBar(navController) })  {

            when(profile) {
                is DataResult.Success -> ProfileScreenContent(profile as DataResult.Success<User>, onEditProfile)
                else -> Unit
            }

        }
    }
}

@Composable
fun ProfileScreenContent(result: DataResult.Success<User>, onEditProfile: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp, vertical = 40.dp)
            .fillMaxSize()) {

        val profile = result.data

        Text(
            text = "Nome Completo:",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(0.9f))
        TextField(
            readOnly = true,
            value = profile.name,
            onValueChange = { profile.name },
            modifier = Modifier.fillMaxWidth(0.9f),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = MaterialTheme.colors.onBackground
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "E-mail:",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(0.9f))
        TextField(
            readOnly = true,
            value = profile.username,
            onValueChange = { profile.username },
            modifier = Modifier.fillMaxWidth(0.9f),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = MaterialTheme.colors.onBackground
            )
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = { onEditProfile.invoke() },
            modifier = Modifier.fillMaxWidth(0.8f),
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary))
        {
            Text(text = "Editar perfil", fontSize = 16.sp)
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .padding(16.dp, vertical = 90.dp)
            .fillMaxSize()){

        Button(onClick = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth(0.8f),
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary)
        ){
            Text(text = "Sair", fontSize = 16.sp)
        }
    }
}

@Composable
@Preview
fun ProfileScreen_Preview() {
    MyApplicationTheme(false){
        ProfileScreen(
            darkTheme = false,
            navController = rememberNavController(),
            onBack = {},
            onEditProfile = {}
        )
    }
}

@Composable
@Preview
fun ProfileScreen_PreviewDark() {
    MyApplicationTheme(true){
        ProfileScreen(
            darkTheme = true,
            navController = rememberNavController(),
            onBack = {},
            onEditProfile = {}
        )
    }
}
