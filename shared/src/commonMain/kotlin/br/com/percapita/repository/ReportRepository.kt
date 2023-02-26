package br.com.percapita.repository

import br.com.percapita.api.PercapitaApi
import br.com.percapita.extension.updateState
import br.com.percapita.model.Report
import br.com.percapita.utils.DataResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * @project PerCapita
 * @author Daniel Augusto on 15/02/2023
 **/
class ReportRepository(
    private val api: PercapitaApi = PercapitaApi.instance,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) {
    suspend fun report() = flow<DataResult<Report>> {
        val dataApi = api.report()
        emit(DataResult.Success(dataApi))
    }.updateState().flowOn(dispatcher)

    companion object {
        val instance by lazy { ReportRepository() }
    }
}