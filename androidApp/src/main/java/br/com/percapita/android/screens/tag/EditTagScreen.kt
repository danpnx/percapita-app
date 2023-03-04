package br.com.percapita.android.screens.tag

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
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
fun EditTagScreen(
    isSystemDarkTheme: Boolean,
    onBack: () -> Unit,
    id: String
) {
    MyApplicationTheme(darkTheme = isSystemDarkTheme) {
        Scaffold(
            topBar = { TopBar(title = "Editar Tag", onBack = onBack) }
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(it)
            ) {
                Spacer(modifier = Modifier.height(150.dp))
                val tagName = remember { mutableStateOf(TextFieldValue()) }
                val viewModel = viewModel<EditTagViewModel>()
                val tagState by viewModel.editTag.collectAsState()
                val context = LocalContext.current

                Spacer(modifier = Modifier.height(180.dp))
                OutlinedTextField(value = tagName.value, onValueChange = { str ->
                    tagName.value = str
                },
                    label = { Text(text = "Digite o nome da Tag") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )

                Button(onClick = {
                    viewModel.editTag(tagName = tagName.value.text, id = id)

                    if(tagState is DataResult.Success) {
                        onBack.invoke()
                        Toast.makeText(context, "Nome atualizado com sucesso!", Toast.LENGTH_SHORT).show()
                    }

                    if(tagState is DataResult.Error) {
                        Toast.makeText(context, "Já existe uma tag com esse nome.", Toast.LENGTH_SHORT).show()
                    }
                },
                    modifier = Modifier.padding(16.dp)) {
                    Text(text = "Atualizar")
                }
            }
        }
    }
}


@Composable
@Preview
fun EditTag_Preview() {
    EditTagScreen(isSystemDarkTheme = false, {}, "")
}

@Composable
@Preview
fun EditTag_DarkPreview() {
    EditTagScreen(isSystemDarkTheme = true, {}, "")
}