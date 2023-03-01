package br.com.percapita.android.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.MoneyOff
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.percapita.android.MyApplicationTheme
import br.com.percapita.android.util.DateUtil.dtf
import br.com.percapita.android.util.DateUtil.locale
import br.com.percapita.enums.TransactionCategory
import br.com.percapita.model.FinancialTransaction
import java.time.Month
import java.time.format.TextStyle


@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TransactionCard(
    transaction: FinancialTransaction,
    onTransactionClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(bottom = 1.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colors.surface),
        onClick = onTransactionClick,
        shape = RectangleShape
    ) {
        val icon = getIcon(transaction.transactionCategory)
        val iconColor = getIconColor(transaction.transactionCategory)

        val isDescriptionEmpty = "" == transaction.transactionDescription

        val cardHeight = if(isDescriptionEmpty) 50.dp else 65.dp

        val month = getMonth(transaction.transactionDate)
        val date = java.time.LocalDate.from(dtf.parse(transaction.transactionDate))
        val fullDate = "${date.dayOfMonth} $month ${date.year}"

        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .padding(10.dp)
                .height(cardHeight)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = "Ícone de transação",
                modifier = Modifier.padding(end = 5.dp)
                    .align(Alignment.CenterVertically),
                tint = iconColor,

            )
            Spacer(modifier = Modifier.width(10.dp))

            Column {
                Text(
                    text = "",
                    color = MaterialTheme.colors.onSurface,
                    fontSize = 17.sp,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.paddingFromBaseline(bottom = 1.dp)
                )
                if(!isDescriptionEmpty) {
                    Text(
                        text = transaction.transactionDescription,
                        color = MaterialTheme.colors.onSurface,
                        fontSize = 14.sp,
                        fontStyle = FontStyle.Normal,
                        modifier = Modifier.paddingFromBaseline(bottom = 1.dp)
                    )
                }
                Text(
                    text = "R$ ${transaction.transactionValue}",
                    color = MaterialTheme.colors.onSurface,
                    fontSize = 14.sp,
                    fontStyle = MaterialTheme.typography.body1.fontStyle,
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = fullDate,
                color = MaterialTheme.colors.onSurface,
                fontSize = 14.sp,
                fontStyle = FontStyle.Normal,
                modifier = Modifier.paddingFromBaseline(bottom = 1.dp).alpha(0.5f)
            )
        }
    }
}

fun getIcon(transactionCategory: TransactionCategory): ImageVector {
    return if(transactionCategory == TransactionCategory.RECEIPT) Icons.Filled.AttachMoney
    else Icons.Filled.MoneyOff
}

@RequiresApi(Build.VERSION_CODES.O)
fun getMonth(date: String): String {
    return Month.from(dtf.parse(date))
        .getDisplayName(TextStyle.SHORT, locale).uppercase()
}

fun getIconColor(transactionCategory: TransactionCategory): Color {
    return if(transactionCategory == TransactionCategory.RECEIPT) return Color(0xFF45C232)
    else Color(0xFFFB6969)
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(name = "Transaction Card Preview - Light", showBackground = true)
@Composable
fun TransactionCardPreview() {
    val transaction = FinancialTransaction(
        "1",
        100.00,
        TransactionCategory.PAYMENT,
        "01/02/2023",
        "",
       // "Jogos"
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
        200.00,
        TransactionCategory.RECEIPT,
        "04/02/2023",
        "Venda de móvel",
       // "Vendas"
    )
    MyApplicationTheme(darkTheme = true) {
        TransactionCard(transaction, onTransactionClick = { })
    }
}