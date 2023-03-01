package br.com.percapita.repository

import br.com.percapita.api.PercapitaApi
import br.com.percapita.extension.updateState
import br.com.percapita.model.FinancialTransaction
import br.com.percapita.utils.DataResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class TransactionRepository(
    private val api: PercapitaApi = PercapitaApi.instance,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) {

    suspend fun registerTransaction(
        financialTransaction: FinancialTransaction) = flow<DataResult<FinancialTransaction>> {
        val dataApi = api.registerTransaction(financialTransaction)
        emit(DataResult.Success(dataApi))
    }.updateState().flowOn(dispatcher)

    suspend fun deleteTransaction(
        financialTransaction: FinancialTransaction) = flow<DataResult<FinancialTransaction>> {
        val dataApi = api.deleteTransaction(financialTransaction)
        emit(DataResult.Success(dataApi))
    }.updateState().flowOn(dispatcher)

    suspend fun getTransactionByCategory(
        financialTransaction: FinancialTransaction) = flow<DataResult<FinancialTransaction>> {
        val dataApi = api.getTransactionByCategory(financialTransaction)
        emit(DataResult.Success(dataApi))
    }.updateState().flowOn(dispatcher)

    suspend fun getTransactionById(
        financialTransaction: FinancialTransaction) = flow<DataResult<FinancialTransaction>> {
        val dataApi = api.getTransactionById(financialTransaction)
        emit(DataResult.Success(dataApi))
    }.updateState().flowOn(dispatcher)

    suspend fun getAllTransactions() = flow<DataResult<List<FinancialTransaction>>> {
        val dataApi = api.getAllTransactions()
        emit(DataResult.Success(dataApi))
    }.updateState().flowOn(dispatcher)

    suspend fun editValue(
        financialTransaction: FinancialTransaction) = flow<DataResult<FinancialTransaction>> {
        val dataApi = api.editValue(financialTransaction)
        emit(DataResult.Success(dataApi))
    }.updateState().flowOn(dispatcher)

    suspend fun editCategory(
        financialTransaction: FinancialTransaction) = flow<DataResult<FinancialTransaction>> {
        val dataApi = api.editCategory(financialTransaction)
        emit(DataResult.Success(dataApi))
    }.updateState().flowOn(dispatcher)

    suspend fun editDate(
        financialTransaction: FinancialTransaction) = flow<DataResult<FinancialTransaction>> {
        val dataApi = api.editDate(financialTransaction)
        emit(DataResult.Success(dataApi))
    }.updateState().flowOn(dispatcher)

    suspend fun editDescription(
        financialTransaction: FinancialTransaction) = flow<DataResult<FinancialTransaction>> {
        val dataApi = api.editDescription(financialTransaction)
        emit(DataResult.Success(dataApi))
    }.updateState().flowOn(dispatcher)

    suspend fun changeTag(
        financialTransaction: FinancialTransaction) = flow<DataResult<FinancialTransaction>> {
        val dataApi = api.changeTag(financialTransaction)
        emit(DataResult.Success(dataApi))
    }.updateState().flowOn(dispatcher)

    suspend fun findByTag(
        financialTransaction: FinancialTransaction) = flow<DataResult<FinancialTransaction>> {
        val dataApi = api.findByTag()
        emit(DataResult.Success(dataApi))
    }.updateState().flowOn(dispatcher)

    companion object {
        val instance by lazy { TransactionRepository() }
    }
}