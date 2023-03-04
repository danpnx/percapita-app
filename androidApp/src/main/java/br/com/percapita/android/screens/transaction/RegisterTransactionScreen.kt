package br.com.percapita.android.screens.register_transaction

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.percapita.android.MyApplicationTheme
import br.com.percapita.android.components.TopBar
import br.com.percapita.android.util.DateUtil.DATE_LENGTH
import br.com.percapita.android.util.DateUtil.DATE_MASK
import br.com.percapita.android.util.MaskVisualTransformation
import br.com.percapita.enums.TransactionCategory
import androidx.compose.material.Text
import androidx.compose.material.Icon
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.percapita.model.Tag

/**
 * @project PerCapita
 * @author Daniel Augusto on 07/02/2023
 **/

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun RegisterTransactionScreen(
    isSystemDarkTheme: Boolean,
    onBack: () -> Unit,
    onOpenTags: () -> Unit,
    onConfirm: () -> Unit
) {
    MyApplicationTheme(darkTheme = isSystemDarkTheme) {
        Scaffold(
            topBar = { TopBar(title = "", onBack) },
            scaffoldState = rememberScaffoldState()
        ) {

            val transactionTypes = listOf("Pagamento", "Recebimento")
            var selectedType by remember { mutableStateOf(transactionTypes[0]) }
            var value by remember { mutableStateOf("") }
            var isExpanded by remember { mutableStateOf(false) }
            var date by remember { mutableStateOf("") }
            var description by remember { mutableStateOf("") }

            val viewModel = viewModel<RegisterTransactionViewModel>()
            val transactionState by viewModel.registerTransaction.collectAsState()


            val tag = remember { mutableStateOf<Tag?>(null)}

            val isButtonEnabled = value != "" && date != "" /*&& tag.value?.tagName != ""*/

            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Registrar Transação",
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    color = MaterialTheme.colors.onBackground
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = date,
                    onValueChange = { str ->
                        if (str.length <= DATE_LENGTH) date = str
                    },
                    label = { Text(text = "Data") },
                    visualTransformation = MaskVisualTransformation(DATE_MASK),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        MaterialTheme.colors.onBackground
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = value,
                    onValueChange = { str ->
                        value = str },
                    label = { Text(text = "Valor") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        MaterialTheme.colors.onBackground
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = description,
                    onValueChange = { str ->
                        if (description.length <= 75) description = str
                    },
                    label = { Text(text = "Descrição") },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        MaterialTheme.colors.onBackground
                    )
                )

//                OutlinedTextField(
//                    value = tag.value?.tagName ?: "",
//                    onValueChange = {
//                        tag.value?.tagName
//                    },
//                    label = { Text(text = tag.value?.tagName ?: "Tag") },
//                    colors = TextFieldDefaults.outlinedTextFieldColors(
//                        MaterialTheme.colors.onBackground
//                    ),
//                    readOnly = true,
//                    trailingIcon = {
//                        IconButton(onClick = onOpenTags) {
//                            Icon(
//                                imageVector = Icons.Filled.ArrowForward,
//                                contentDescription = "Ir para tags",
//                                tint = MaterialTheme.colors.primary
//                            )
//                        }
//                    }
//                )

                Spacer(modifier = Modifier.height(24.dp))

                ExposedDropdownMenuBox(
                    expanded = isExpanded,
                    onExpandedChange = { isExpanded = !isExpanded }
                ) {
                    TextField(
                        value = selectedType,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text(text = "Tipo da Transação") },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                        },
                        colors = ExposedDropdownMenuDefaults.textFieldColors()
                    )

                    ExposedDropdownMenu(
                        expanded = isExpanded,
                        onDismissRequest = { isExpanded = false }
                    ) {
                        transactionTypes.forEach { selectedOption ->
                            DropdownMenuItem(
                                onClick = {
                                    selectedType = selectedOption
                                    isExpanded = false
                                }
                            ) {
                                Text(text = selectedOption)
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(30.dp))

                Button(
                    onClick = {
                              viewModel.registerTransaction(
                                  transactionId = null,
                                  transactionValue = value.replace(",", ".").toDouble(),
                                  transactionCategory = if(selectedType == "Pagamento") TransactionCategory.PAYMENT
                                  else TransactionCategory.RECEIPT,
                                  transactionDate = convertToDate(date),
                                  transactionDescription = description,
                                  //tagName = tag.value?.tagName ?: ""
                              )
                    },
                    enabled = isButtonEnabled,
                    contentPadding = PaddingValues(5.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
                    modifier = Modifier.alpha(
                        if(isButtonEnabled) 1f else 0.50f
                    )
                ) {
                    Text(
                        text = "Confirmar",
                        color = if(isButtonEnabled) MaterialTheme.colors.onPrimary
                        else Color.LightGray
                    )
                }
            }
        }
    }
}

private fun convertToDate(dateStr: String): String {
    var date = ""
    var count = 0
    for(n in dateStr) {
        if(count == 2 || count == 4) {
            date += "/"
        }
        date += n
        count++
    }
    return date
}

@Preview(name = "Register Transaction Screen Preview - Light")
@Composable
private fun RegisterTransactionScreenPreview() {
    RegisterTransactionScreen(isSystemDarkTheme = false, onBack = {}, onOpenTags = {}, onConfirm = {})
}

@Preview(name = "Register Transaction Screen Preview - Dark")
@Composable
private fun RegisterTransactionScreenPreviewDark() {
    RegisterTransactionScreen(isSystemDarkTheme = true, onBack = {}, onOpenTags = {}, onConfirm = {})
}