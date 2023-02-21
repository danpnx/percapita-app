package br.com.percapita.android.screens.tag

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.Button
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.percapita.android.MyApplicationTheme
import br.com.percapita.android.components.TopBar
import br.com.percapita.utils.DataResult

@Composable
fun CreateTagScreen(isSystemDarkTheme: Boolean, onBack: () -> Unit, onTagScreenNavigation: () -> Unit) {
    MyApplicationTheme(darkTheme = isSystemDarkTheme) {
        Scaffold(
            topBar = { TopBar(title = "Criar Tag", onBack = onBack) }
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(it)
            ) {
                Spacer(modifier = Modifier.height(300.dp))
                val tagName = remember { mutableStateOf(TextFieldValue()) }
                val viewModel = viewModel<CreateTagViewModel>()
                val tagState by viewModel.registerTag.collectAsState()
                val context = LocalContext.current
                
                Spacer(modifier = Modifier.height(180.dp))
                OutlinedTextField(value = tagName.value, onValueChange = { str ->
                    tagName.value = str
                },
                    label = {Text(text = "Digite o nome da Tag")},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )

                Button(onClick = {
                                 viewModel.registerTag(tagName = tagName.value.text, id = null)

                    if(tagState is DataResult.Success) {
                        onTagScreenNavigation.invoke()
                        Toast.makeText(context, "Tag ${tagName.value.text} criada com sucesso!", Toast.LENGTH_SHORT).show()
                    }

                    if(tagState is DataResult.Error) {
                        Toast.makeText(context, "Tag ${tagName.value.text} já existe", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.padding(16.dp)) {
                    Text(text = "Criar Tag")
                }
            }
        }
    }
}

@Composable
@Preview
fun NewTagScreen_Preview() {
    CreateTagScreen(isSystemDarkTheme = false, onBack = {}, {})
}

@Composable
@Preview
fun NewTagScreenDark_Preview() {
    CreateTagScreen(isSystemDarkTheme = true, onBack = {}, {})
}