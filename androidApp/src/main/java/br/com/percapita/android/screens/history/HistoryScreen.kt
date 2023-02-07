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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.percapita.android.MyApplicationTheme
import br.com.percapita.android.components.BottomBar
import br.com.percapita.android.components.TopBar
import br.com.percapita.android.components.TransactionCard
import br.com.percapita.android.util.Lists.transactionList

/**
 * @project PerCapita
 * @author Daniel Augusto on 04/02/2023
 **/

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HistoryScreen(isSystemDarkTheme: Boolean) {
    MyApplicationTheme(darkTheme = isSystemDarkTheme) {
        Scaffold(
            bottomBar = { BottomBar() },
            topBar = { TopBar(title = "Histórico") },
            floatingActionButton = { AddTransactionFAB() }
        ) {
            LazyColumn(modifier = Modifier.padding(it)) {
                items(transactionList) { transaction ->
                    TransactionCard(transaction = transaction, onTransactionClick = {})
                }
            }
        }
    }
}

@Composable
fun AddTransactionFAB() {
    FloatingActionButton(
        onClick = {},
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
    HistoryScreen(false)
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(name = "History Screen Preview - Dark")
@Composable
fun HistoryScreenPreviewDark() {
    HistoryScreen(true)
}

@Preview(name = "Add Transaction FAB Preview - Light", showBackground = true)
@Composable
fun AddTransactionFABPreview() {
    MyApplicationTheme(darkTheme = false) {
        AddTransactionFAB()
    }
}

@Preview(name = "Add Transaction FAB Preview - Dark", showBackground = true)
@Composable
fun AddTransactionFABDark() {
    MyApplicationTheme(darkTheme = true) {
        AddTransactionFAB()
    }
}