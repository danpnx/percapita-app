package br.com.percapita.android.screens.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.percapita.model.FinancialTransaction
import br.com.percapita.repository.TransactionRepository
import br.com.percapita.utils.DataResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HistoryScreenViewModel(
    private val repository: TransactionRepository = TransactionRepository.instance
): ViewModel() {

    init {
        getAllTransaction()
    }

    private val _transaction: MutableStateFlow<DataResult<List<FinancialTransaction>>> =
        MutableStateFlow(DataResult.Empty)
    val transaction: StateFlow<DataResult<List<FinancialTransaction>>> = _transaction

    private fun getAllTransaction() = viewModelScope.launch {
        repository.getAllTransactions().collectLatest {
            _transaction.value = it
        }
    }
}