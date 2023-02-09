package br.com.percapita.payload

import br.com.percapita.enums.TransactionCategory

/**
 * @project PerCapita
 * @author Daniel Augusto on 07/02/2023
 **/
class FinancialTransactionPayload(
    transactionValue: String,
    transactionCategory: TransactionCategory,
    transactionDate: String,
    transactionDescription: String
)