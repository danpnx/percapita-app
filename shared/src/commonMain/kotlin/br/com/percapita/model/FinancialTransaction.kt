package br.com.percapita.model

import br.com.percapita.enums.TransactionCategory
import kotlinx.serialization.Serializable

@Serializable
data class FinancialTransaction(
    val transactionId: String,
    val transactionValue: Double,
    val transactionCategory: TransactionCategory,
    val transactionDate: String,
    val transactionDescription: String,
    //val tagName: String
)