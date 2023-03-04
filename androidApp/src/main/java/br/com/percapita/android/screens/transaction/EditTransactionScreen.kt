@file:OptIn(ExperimentalMaterialApi::class)

package br.com.percapita.android.screens.transaction

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.percapita.android.MyApplicationTheme
import br.com.percapita.android.components.TopBar
import br.com.percapita.android.util.DateUtil
import br.com.percapita.android.util.MaskVisualTransformation
import br.com.percapita.enums.TransactionCategory
import br.com.percapita.model.FinancialTransaction
import br.com.percapita.utils.DataResult

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EditTransactionScreen(
    isSystemDarkTheme: Boolean,
    onBack: () -> Unit,
    id: String,
    onSaveChanges: () -> Unit
) {
    MyApplicationTheme(isSystemDarkTheme) {
        Scaffold(modifier = Modifier.fillMaxSize(),
            backgroundColor = MaterialTheme.colors.background,
            contentColor = MaterialTheme.colors.onBackground,
            topBar = { TopBar(title = "Editar Transação", onBack) },
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(it)
            ) {
                val transactionTypes = listOf("Pagamento", "Recebimento")
                var selectedType by remember { mutableStateOf(transactionTypes[0]) }
                var value by remember { mutableStateOf("") }
                var isExpanded by remember { mutableStateOf(false) }
                var date by remember { mutableStateOf("") }
                var description by remember { mutableStateOf("") }
                val context = LocalContext.current

                val viewModel = viewModel<EditTransactionViewModel>()
                val transactionState by viewModel.editTransaction.collectAsState()


                val isButtonEnabled = value != "" && date != ""

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

                    androidx.compose.material3.OutlinedTextField(
                        value = date,
                        onValueChange = { str ->
                            if (str.length <= DateUtil.DATE_LENGTH) date = str
                        },
                        label = { Text(text = "Data") },
                        visualTransformation = MaskVisualTransformation(DateUtil.DATE_MASK),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        colors = androidx.compose.material3.TextFieldDefaults.outlinedTextFieldColors(
                            MaterialTheme.colors.onBackground
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    androidx.compose.material3.OutlinedTextField(
                        value = value,
                        onValueChange = { str ->
                            value = str
                        },
                        label = { Text(text = "Valor") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        colors = androidx.compose.material3.TextFieldDefaults.outlinedTextFieldColors(
                            MaterialTheme.colors.onBackground
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    androidx.compose.material3.OutlinedTextField(
                        value = description,
                        onValueChange = { str ->
                            if (description.length <= 75) description = str
                        },
                        label = { Text(text = "Descrição") },
                        colors = androidx.compose.material3.TextFieldDefaults.outlinedTextFieldColors(
                            MaterialTheme.colors.onBackground
                        )
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

                    if(transactionState is DataResult.Success) {
                        Toast.makeText(context, "Transação atualizada com sucesso!", Toast.LENGTH_SHORT).show()
                        viewModel.defaultState()
                        onBack.invoke()
                    }

                    if(transactionState is DataResult.Error) {
                        Toast.makeText(context, "Não foi possivel atualizar a transação," +
                                "verifique os campos e tente novamente", Toast.LENGTH_SHORT).show()
                        viewModel.defaultState()
                    }

                    Button(
                        onClick = {
                            viewModel.editTransaction(
                                transactionId = id,
                                transactionValue = value.replace(",", ".").toDouble(),
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
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ContentEditTransactionScreen(result: DataResult.Success<FinancialTransaction>, onBack: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val trans = result.data
        var date by remember { mutableStateOf("") }
        val value = remember { mutableStateOf(TextFieldValue()) }
        val description = remember { mutableStateOf(TextFieldValue()) }
        val transactionTypes = listOf("Pagamento", "Recebimento")
        var selectedType by remember { mutableStateOf(transactionTypes[0]) }
        var isExpanded by remember { mutableStateOf(false) }
        val viewModel = viewModel<EditTransactionViewModel>()
        val editState = viewModel.editTransaction.collectAsState()

        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Data:",
            textAlign = TextAlign.Left
        )

        androidx.compose.material3.OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(16.dp),
            value = date,
            onValueChange = { str ->
                if (str.length <= DateUtil.DATE_LENGTH) date = str
            },
            label = { Text(text = "${trans.transactionDate}") },
            visualTransformation = MaskVisualTransformation(DateUtil.DATE_MASK),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = androidx.compose.material3.TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = MaterialTheme.colors.onBackground,
                unfocusedLabelColor = MaterialTheme.colors.onSurface
            )
        )

        Text(
            text = "Valor:",
            textAlign = TextAlign.Left
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(16.dp),
            value = value.value,
            onValueChange = { value.value = it },
            label = { Text(text = "${trans.transactionValue}") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = MaterialTheme.colors.onBackground,
                unfocusedLabelColor = MaterialTheme.colors.onSurface
            )
        )

        Text(
            text = "Descrição:",
            textAlign = TextAlign.Left
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(16.dp),
            value = description.value,
            onValueChange = { description.value = it },
            label = { Text(text = "${trans.transactionDescription}") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = MaterialTheme.colors.onBackground,
                unfocusedLabelColor = MaterialTheme.colors.onSurface
            )
        )

        Spacer(modifier = Modifier.height(30.dp))
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

        Spacer(modifier = Modifier.height(100.dp))
        Button(
            onClick = {
                viewModel.editTransaction(
                    transactionId = trans.transactionId,
                    transactionValue = value.value.text.replace(",", ".").toDouble(),
                    transactionCategory = if(selectedType == "Pagamento") TransactionCategory.PAYMENT
                    else TransactionCategory.RECEIPT,
                    transactionDate = convertToDate(date),
                    transactionDescription = description.value.text
                )
                onBack.invoke() },
        ) {
            Text(
                text = "Confirmar",
            )
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