package br.com.percapita.android.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.percapita.android.MyApplicationTheme
import br.com.percapita.enums.TransactionCategory
import br.com.percapita.model.FinancialTransaction
import java.time.DayOfWeek
import java.time.Month
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*

/**
 * @project PerCapita
 * @author Daniel Augusto on 03/02/2023
 **/

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TransactionCard(
    transaction: FinancialTransaction,
    onTransactionClick: () -> Unit
) {
    Card(
        shape = CardDefaults.outlinedShape,
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(3.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colors.surface),
        border = BorderStroke(0.1.dp, MaterialTheme.colors.secondaryVariant),
        onClick = onTransactionClick
    ) {
        val transactionStr = transactionValueStr(
            transaction.transactionCategory,
            transaction.transactionValue
        )

        val transactionColor = transactionColorValue(transaction.transactionCategory)

        val description = if(transaction.transactionDescription == "") "Transação sem descrição"
        else transaction.transactionDescription

        val dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy")

        val dow = DayOfWeek.from(dtf.parse(transaction.transactionDate))
            .getDisplayName(TextStyle.FULL, Locale("pt", "BR"))

        val month = Month.from(dtf.parse(transaction.transactionDate))
            .getDisplayName(TextStyle.FULL_STANDALONE, Locale("pt", "BR"))

        val date = java.time.LocalDate.from(dtf.parse(transaction.transactionDate))

        val fullDate = "${date.dayOfMonth} de $month de ${date.year}"
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Top,
            modifier = Modifier.padding(10.dp)
        ) {
            Column() {
                Text(
                    text = transaction.tagName,
                    color = MaterialTheme.colors.onSurface,
                    fontSize = MaterialTheme.typography.overline.fontSize,
                    fontStyle = MaterialTheme.typography.overline.fontStyle,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = description,
                    color = MaterialTheme.colors.onSurface,
                    fontSize = MaterialTheme.typography.body1.fontSize,
                    fontStyle = MaterialTheme.typography.body1.fontStyle
                )
                Text(
                    text = transactionStr,
                    color = transactionColor,
                    fontSize = MaterialTheme.typography.body1.fontSize,
                    fontStyle = MaterialTheme.typography.body1.fontStyle
                )
                Text(
                    text = fullDate,
                    color = MaterialTheme.colors.onSurface,
                    fontSize = MaterialTheme.typography.body2.fontSize,
                    fontStyle = MaterialTheme.typography.body2.fontStyle
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(text = dow, color = MaterialTheme.colors.onSurface)
        }
    }
}

fun transactionColorValue(transactionCategory: TransactionCategory): Color {
    return if(transactionCategory == TransactionCategory.RECEIPT) return Color(0xFF45C232)
    else Color(0xFFFB6969)
}

fun transactionValueStr(
    transactionCategory: TransactionCategory,
    transactionValue: String
): String {
    return if(transactionCategory == TransactionCategory.valueOf("RECEIPT")) "+ R$ $transactionValue"
    else "- R$ $transactionValue"
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(name = "Transaction Card Preview - Light", showBackground = true)
@Composable
fun TransactionCardPreview() {
    val transaction = FinancialTransaction(
        "1",
        "100.00",
        TransactionCategory.PAYMENT,
        "03/02/2023",
        "Cyberpunk 2077",
        "Jogos",
        listOf()
    )
    MyApplicationTheme(darkTheme = false) {
        TransactionCard(transaction, onTransactionClick = { })
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(name = "Transaction Card Preview - Dark", showBackground = true)
@Composable
fun TransactionCardPreviewDark() {
    val transaction = FinancialTransaction(
        "2",
        "200.00",
        TransactionCategory.RECEIPT,
        "03/02/2023",
        "Venda de móvel",
        "Vendas",
        listOf()
    )
    MyApplicationTheme(darkTheme = true) {
        TransactionCard(transaction, onTransactionClick = { })
    }
}