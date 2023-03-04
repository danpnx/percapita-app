package br.com.percapita.android.screens.transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.percapita.enums.TransactionCategory
import br.com.percapita.model.FinancialTransaction
import br.com.percapita.repository.TransactionRepository
import br.com.percapita.utils.DataResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class EditTransactionViewModel(
    private val repository: TransactionRepository = TransactionRepository.instance
): ViewModel() {


    private val _editTransaction = MutableStateFlow<DataResult<FinancialTransaction>>(DataResult.Empty)
    val editTransaction: StateFlow<DataResult<FinancialTransaction>> = _editTransaction

    private val _transaction = MutableStateFlow<DataResult<FinancialTransaction>>(DataResult.Empty)
    val transaction: StateFlow<DataResult<FinancialTransaction>> = _transaction

    private val _editValue: MutableStateFlow<DataResult<FinancialTransaction>> = MutableStateFlow(DataResult.Empty)
    val editValue: StateFlow<DataResult<FinancialTransaction>> = _editValue

    private val _editCategory: MutableStateFlow<DataResult<FinancialTransaction>> = MutableStateFlow(DataResult.Empty)
    val editCategory: StateFlow<DataResult<FinancialTransaction>> = _editCategory

    private val _editDate: MutableStateFlow<DataResult<FinancialTransaction>> = MutableStateFlow(DataResult.Empty)
    val editDate: StateFlow<DataResult<FinancialTransaction>> = _editDate

    private val _editDescription: MutableStateFlow<DataResult<FinancialTransaction>> = MutableStateFlow(DataResult.Empty)
    val editDescription: StateFlow<DataResult<FinancialTransaction>> = _editDescription


    fun getTransaction(transactionId: String) = viewModelScope.launch {
        repository.getTransactionById(transactionId).collectLatest {
            _transaction.value = it
        }
    }

    fun editValue(
        transaction: FinancialTransaction,
        newValue: Double
    ) = viewModelScope.launch {
        val edit = transaction.copy(transactionValue = newValue)

        repository.editValue(edit).collectLatest {
            _editValue.value = it
        }
    }

    fun editCategory(
        transaction: FinancialTransaction,
        newCategory: TransactionCategory
    ) = viewModelScope.launch {
        val edit = transaction.copy(transactionCategory = newCategory)

        repository.editValue(edit).collectLatest {
            _editValue.value = it
        }
    }

    fun editDate(
        transaction: FinancialTransaction,
        newDate: String
    ) = viewModelScope.launch {
        val edit = transaction.copy(transactionDate = newDate)

        repository.editValue(edit).collectLatest {
            _editValue.value = it
        }
    }

    fun editDescription(
        transaction: FinancialTransaction,
        newDescription: String
    ) = viewModelScope.launch {
        val edit = transaction.copy(transactionDescription = newDescription)

        repository.editValue(edit).collectLatest {
            _editValue.value = it
        }
    }

    fun editTransaction(
        transactionId: String?,
        transactionValue: Double?,
        transactionCategory: TransactionCategory?,
        transactionDate: String?,
        transactionDescription: String?,
    ) = viewModelScope.launch {

        val edit = FinancialTransaction(
            transactionId = transactionId ?: "",
        transactionValue = transactionValue ?: 0.0,
        transactionCategory = (transactionCategory ?: "") as TransactionCategory,
        transactionDate = transactionDate ?: "",
        transactionDescription = transactionDescription ?: ""
        )

        repository.editTransaction(edit).collectLatest {
            _editTransaction.value = it
        }
    }

    fun defaultState() {
        _editTransaction.value = DataResult.Empty
    }
}