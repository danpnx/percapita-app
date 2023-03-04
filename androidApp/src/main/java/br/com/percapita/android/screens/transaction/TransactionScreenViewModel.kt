package br.com.percapita.android.screens.transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.percapita.model.FinancialTransaction
import br.com.percapita.repository.TransactionRepository
import br.com.percapita.utils.DataResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class TransactionScreenViewModel(
    private val repository: TransactionRepository = TransactionRepository.instance
): ViewModel() {

    private val _transaction = MutableStateFlow<DataResult<FinancialTransaction>>(DataResult.Empty)
    val transaction: StateFlow<DataResult<FinancialTransaction>> = _transaction

    private val _deleteTransaction = MutableStateFlow<DataResult<FinancialTransaction>>(DataResult.Empty)
    val deleteTransaction: StateFlow<DataResult<FinancialTransaction>> = _deleteTransaction

    fun getTransaction(transactionId: String) = viewModelScope.launch {
        repository.getTransactionById(transactionId).collectLatest {
            _transaction.value = it
        }
    }

    fun deleteTransaction(transactionId: String) = viewModelScope.launch {
        repository.deleteTransaction(transactionId).collectLatest {
            _deleteTransaction.value = it
        }
    }
}