package br.com.percapita.model

import br.com.percapita.enums.TransactionCategory

/**
 * @project PerCapita
 * @author Daniel Augusto on 03/02/2023
 **/
data class FinancialTransaction(
    val transactionId: String,
    val transactionValue: String,
    val transactionCategory: TransactionCategory,
    val transactionDate: String,
    val transactionDescription: String,
    val tagName: String,
    val links: List<String>
)