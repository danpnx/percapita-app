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
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.percapita.android.MyApplicationTheme
import br.com.percapita.android.components.BottomBar
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
            topBar = { HistoryTopBar(title = "Histórico") },
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
fun HistoryTopBar(title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .paddingFromBaseline(bottom = 50.dp)
            .padding(top = 1.5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Filled.ArrowBackIosNew,
                contentDescription = "Voltar",
                tint = MaterialTheme.colors.onBackground,
                modifier = Modifier.padding(start = 10.dp)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = title,
            color = MaterialTheme.colors.onBackground,
            fontSize = 25.sp,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(2.5f)
        )
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