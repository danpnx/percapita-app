package br.com.percapita.android.screens.register_transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.percapita.enums.TransactionCategory
import br.com.percapita.model.FinancialTransaction
import br.com.percapita.repository.TransactionRepository
import br.com.percapita.utils.DataResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class RegisterTransactionViewModel(
    private val repository: TransactionRepository = TransactionRepository.instance
): ViewModel() {

    private val _registerTransaction: MutableStateFlow<DataResult<FinancialTransaction>> = MutableStateFlow(DataResult.Empty)
    val registerTransaction: StateFlow<DataResult<FinancialTransaction>> = _registerTransaction

    fun registerTransaction(
        transactionId: String?,
        transactionValue: Double,
        transactionCategory: TransactionCategory,
        transactionDate: String,
        transactionDescription: String?,
        //tagName: String
    ) = viewModelScope.launch {

        val register = FinancialTransaction(transactionId = transactionId ?: "",
            transactionValue = transactionValue,
            transactionCategory = transactionCategory,
            transactionDate = transactionDate,
            transactionDescription = transactionDescription ?: "",
            //tagName = tagName
            )

        repository.registerTransaction(register).collectLatest {
            _registerTransaction.value = it
        }
    }
}