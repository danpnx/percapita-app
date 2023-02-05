package br.com.percapita.android.util

import br.com.percapita.enums.TransactionCategory
import br.com.percapita.model.FinancialTransaction
import br.com.percapita.model.Tag

object Lists {
    val transactionList = listOf(
        FinancialTransaction(
            "10",
            "17.55",
            TransactionCategory.PAYMENT,
            "05/02/2023",
            "",
            "Padaria",
            listOf()
        ),
        FinancialTransaction(
            "9",
            "99.50",
            TransactionCategory.PAYMENT,
            "04/02/2023",
            "",
            "Supermercado",
            listOf()
        ),
        FinancialTransaction(
            "8",
            "200.00",
            TransactionCategory.RECEIPT,
            "04/02/2023",
            "Venda de móvel",
            "Vendas",
            listOf()
        ),
        FinancialTransaction(
            "7",
            "21.35",
            TransactionCategory.PAYMENT,
            "03/02/2023",
            "Uber para o teatro",
            "Uber",
            listOf()
        ),
        FinancialTransaction(
            "6",
            "44.90",
            TransactionCategory.PAYMENT,
            "02/02/2023",
            "Xbox Game Pass",
            "Assinaturas",
            listOf()
        ),
        FinancialTransaction(
            "5",
            "27.90",
            TransactionCategory.PAYMENT,
            "02/02/2023",
            "HBO Max",
            "Assinaturas",
            listOf()
        ),
        FinancialTransaction(
            "4",
            "34.90",
            TransactionCategory.PAYMENT,
            "02/02/2023",
            "Spotify Family",
            "Assinaturas",
            listOf()
        ),
        FinancialTransaction(
            "3",
            "27.90",
            TransactionCategory.PAYMENT,
            "02/02/2023",
            "HBO Max",
            "Assinaturas",
            listOf()
        ),
        FinancialTransaction(
            "2",
            "1200",
            TransactionCategory.RECEIPT,
            "01/02/2023",
            "Salário",
            "Salário",
            listOf()
        ),
        FinancialTransaction(
            "1",
            "100.00",
            TransactionCategory.PAYMENT,
            "01/02/2023",
            "",
            "Jogos",
            listOf()
        )
    )
    val tagList = listOf(
        Tag(
            tagId = "1",
            tagName = "Padaria"
        ),
        Tag(
            tagId = "2",
            tagName = "Supermercado"
        ),
        Tag(
            tagId = "3",
            tagName = "Vendas"
        ),
        Tag(
            tagId = "4",
            tagName = "Pizzaria"
        ),
        Tag(
            tagId = "5",
            tagName = "Uber"
        ),
        Tag(
            tagId = "6",
            tagName = "Assinaturas"
        ),
        Tag(
            tagId = "7",
            tagName = "Salário"
        ),
        Tag(
            tagId = "8",
            tagName = "Jogos"
        ),
        Tag(
            tagId = "9",
            tagName = "Investimento"
        ),
        Tag(
            tagId = "10",
            tagName = "Restaurante"
        )
    )
}