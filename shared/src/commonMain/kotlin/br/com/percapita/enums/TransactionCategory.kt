package br.com.percapita.enums

/**
 * @project PerCapita
 * @author Daniel Augusto on 03/02/2023
 **/
enum class TransactionCategory(val description: String) {
    PAYMENT("Pagamento"),
    RECEIPT("Recebimento")
}