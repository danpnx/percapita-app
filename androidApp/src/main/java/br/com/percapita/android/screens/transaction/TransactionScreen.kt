package br.com.percapita.android.screens.transaction

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material.Text
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.percapita.android.MyApplicationTheme
import br.com.percapita.android.components.TopBar
import br.com.percapita.model.FinancialTransaction
import br.com.percapita.utils.DataResult


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TransactionScreen(
    isSystemDarkTheme: Boolean,
    onBack: () -> Unit,
    onDeleteTransaction: (String) -> Unit,
    onEditTransaction: (String) -> Unit,
    id: String
) {
    MyApplicationTheme(darkTheme = isSystemDarkTheme) {
        val viewModel = viewModel<TransactionScreenViewModel>()
        viewModel.getTransaction(id)
        val transactions by viewModel.transaction.collectAsState()

        Scaffold(
            topBar = { TopBar(title = "Transação", onBack = onBack) }
        ) { _ ->

            when(transactions) {
                is DataResult.Success -> ContentTransactionScreen(transactions as DataResult.Success<FinancialTransaction>, onDeleteTransaction, onEditTransaction)
                else -> {}
            }
        }
    }
}

@Composable
fun ContentTransactionScreen(
    result: DataResult.Success<FinancialTransaction>,
    onDeleteTransaction: (String) -> Unit,
    onEditTransaction: (String) -> Unit
) {
    println("tela transacao")
    val transaction = result.data
    val viewModel: TransactionScreenViewModel = viewModel()
    val showDialog = remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Spacer(modifier = Modifier.height(10.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(3.dp)
                .height(50.dp),
            colors = CardDefaults.cardColors(MaterialTheme.colors.surface),
            shape = RectangleShape
        ) {
            Text(
                text = "Descrição:",
                fontSize = 17.sp,
                color = MaterialTheme.colors.onSurface,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = transaction.transactionDescription,
                fontSize = 16.sp,
                modifier = Modifier.padding(horizontal = 6.dp),
                color = MaterialTheme.colors.onSurface
            )
        }

        Spacer(modifier = Modifier.height(5.dp))
        Divider()
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(3.dp)
                .height(50.dp),
            colors = CardDefaults.cardColors(MaterialTheme.colors.surface),
            shape = RectangleShape
        ) {
            Text(
                text = "Valor:",
                fontSize = 17.sp,
                color = MaterialTheme.colors.onSurface,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "R$ ${transaction.transactionValue}",
                fontSize = 16.sp,
                modifier = Modifier.padding(horizontal = 6.dp),
                color = MaterialTheme.colors.onSurface
            )
        }

        Spacer(modifier = Modifier.height(5.dp))
        Divider()
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(3.dp)
                .height(50.dp),
            colors = CardDefaults.cardColors(MaterialTheme.colors.surface),
            shape = RectangleShape
        ) {
            Text(
                text = "Data:",
                fontSize = 17.sp,
                color = MaterialTheme.colors.onSurface,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = transaction.transactionDate,
                fontSize = 16.sp,
                modifier = Modifier.padding(horizontal = 6.dp),
                color = MaterialTheme.colors.onSurface
            )
        }

        Spacer(modifier = Modifier.height(80.dp))
        Button(onClick = { onEditTransaction.invoke(transaction.transactionId) },
            modifier = Modifier.fillMaxWidth(0.8f),
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary))
        {
            Text(text = "Editar Transação", fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = {
            showDialog.value = true },
            modifier = Modifier.fillMaxWidth(0.8f),
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary))
        {
            Text(text = "Excluir transação", fontSize = 16.sp)
        }
    }

    if(showDialog.value) {
        AlertDialog(
            modifier = Modifier.background(
                color = MaterialTheme.colors.surface),
            title = { Text(text = "") },
            text = {
                Text(
                    text = "Você deseja deletar essa transação?",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onBackground
                )
            },
            onDismissRequest = { showDialog.value = false },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.deleteTransaction(transaction.transactionId)
                    onDeleteTransaction(transaction.transactionId)
                    Toast.makeText(context, "Transação deletada com sucesso!", Toast.LENGTH_SHORT).show()
                }) {
                    Text(text = "Sim",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = MaterialTheme.colors.onBackground)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog.value = false }) {
                    Text(text = "Não",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = MaterialTheme.colors.onBackground)
                }
            }
        )
    }
}