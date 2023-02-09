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
import br.com.percapita.payload.FinancialTransactionPayload

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
            var value by remember {
                mutableStateOf("")
            }
            var selectedType by remember {
                mutableStateOf(transactionTypes[0])
            }
            var isExpanded by remember {
                mutableStateOf(false)
            }
            var date by remember {
                mutableStateOf("")
            }
            var description by remember {
                mutableStateOf("")
            }
            var tag by remember {
                mutableStateOf("")
            }
            val isButtonEnabled = value != "" && date != "" && tag != ""

            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                androidx.compose.material.Text(
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
                    label = { androidx.compose.material.Text(text = "Data") },
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
                        value = str
                    },
                    label = { androidx.compose.material.Text(text = "Valor") },
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
                    label = { androidx.compose.material.Text(text = "Descrição") },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        MaterialTheme.colors.onBackground
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = tag,
                    onValueChange = { str ->
                        tag = str
                    },
                    label = { androidx.compose.material.Text(text = "Tag") },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        MaterialTheme.colors.onBackground
                    ),
                    readOnly = true,
                    trailingIcon = {
                        IconButton(onClick = onOpenTags) {
                            androidx.compose.material.Icon(
                                imageVector = Icons.Filled.ArrowForward,
                                contentDescription = "Ir para tags",
                                tint = MaterialTheme.colors.primary
                            )
                        }
                    }
                )

                Spacer(modifier = Modifier.height(24.dp))

                ExposedDropdownMenuBox(
                    expanded = isExpanded,
                    onExpandedChange = { isExpanded = !isExpanded }
                ) {
                    TextField(
                        value = selectedType,
                        onValueChange = {},
                        readOnly = true,
                        label = { androidx.compose.material.Text(text = "Tipo da Transação") },
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
                                androidx.compose.material.Text(text = selectedOption)
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(30.dp))

                Button(
                    onClick = {
                              FinancialTransactionPayload(
                                  transactionValue = value,
                                  transactionCategory = if(selectedType == "Pagamento") TransactionCategory.PAYMENT
                                  else TransactionCategory.RECEIPT,
                                  transactionDate = convertToDate(date),
                                  transactionDescription = description
                              )
                    },
                    enabled = isButtonEnabled,
                    contentPadding = PaddingValues(5.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
                    modifier = Modifier.alpha(
                        if(isButtonEnabled) 1f else 0.50f
                    )
                ) {
                    androidx.compose.material.Text(
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