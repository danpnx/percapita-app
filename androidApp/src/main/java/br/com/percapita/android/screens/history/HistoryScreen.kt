package br.com.percapita.android.screens.history

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.percapita.android.MyApplicationTheme
import br.com.percapita.android.components.BottomBar
import br.com.percapita.android.components.TopBar
import br.com.percapita.android.components.TransactionCard
import br.com.percapita.model.FinancialTransaction
import br.com.percapita.utils.DataResult


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HistoryScreen(
    isSystemDarkTheme: Boolean,
    navController: NavController,
    onBack: () -> Unit,
    onClickRegisterTransaction: () -> Unit
) {
    MyApplicationTheme(darkTheme = isSystemDarkTheme) {
        val viewModel = viewModel<HistoryScreenViewModel>()
        val historyState by viewModel.transaction.collectAsState()

        Scaffold(
            bottomBar = { BottomBar(navController) },
            topBar = { TopBar(title = "Histórico", onBack = onBack) },
            floatingActionButton = { AddTransactionFAB(onClickRegisterTransaction) }
        ) { _ ->
            when(historyState) {
                is DataResult.Success -> ContentHistoryScreen(
                    historyState as DataResult.Success<List<FinancialTransaction>>
                )
                else -> ContentHistoryScreenEmpty()
            }
        }
    }
}

@Composable
fun ContentHistoryScreen(result: DataResult.Success<List<FinancialTransaction>>) {
    val transaction = result.data
    LazyColumn(
        modifier = Modifier.padding()) {
        items(transaction) { transaction ->
            TransactionCard(transaction = transaction, onTransactionClick = {})
        }
    }
}

@Composable
fun ContentHistoryScreenEmpty() {
    Column(
        modifier = Modifier.padding(60.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Spacer(modifier = Modifier.height(200.dp))
        Text(
            text = "Nenhum lançamento no momento",
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onBackground,
            fontSize = 18.sp,
            )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Toque em  +",
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colors.onBackground,
            fontSize = 17.sp)
        Text(
            text = "para adicionar uma nova transação.",
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colors.onBackground,
            fontSize = 17.sp
        )
    }
}


@Composable
fun AddTransactionFAB(onClickRegisterTransaction: () -> Unit) {
    FloatingActionButton(
        onClick = onClickRegisterTransaction,
        shape = CircleShape,
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary,
        elevation = FloatingActionButtonDefaults.elevation(7.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Registrar Transação"
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(name = "History Screen Preview - Light")
@Composable
fun HistoryScreenPreview() {
    HistoryScreen(
        isSystemDarkTheme = false,
        navController = rememberNavController(),
        onBack = {},
        onClickRegisterTransaction = {}
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(name = "History Screen Preview - Dark")
@Composable
fun HistoryScreenPreviewDark() {
    HistoryScreen(
        isSystemDarkTheme = true,
        navController = rememberNavController(),
        onBack = {},
        onClickRegisterTransaction = {}
    )
}

@Preview(name = "Add Transaction FAB Preview - Light", showBackground = true)
@Composable
fun AddTransactionFABPreview() {
    MyApplicationTheme(darkTheme = false) {
        AddTransactionFAB(onClickRegisterTransaction = {})
    }
}

@Preview(name = "Add Transaction FAB Preview - Dark", showBackground = true)
@Composable
fun AddTransactionFABDark() {
    MyApplicationTheme(darkTheme = true) {
        AddTransactionFAB(onClickRegisterTransaction = {})
    }
}

@Preview
@Composable
fun ContentHistoryScreenEmptyPreview() {
    ContentHistoryScreenEmpty()
}