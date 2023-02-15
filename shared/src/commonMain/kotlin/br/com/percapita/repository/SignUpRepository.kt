package br.com.percapita.repository

import br.com.percapita.api.PercapitaApi
import br.com.percapita.extension.updateState
import br.com.percapita.model.User
import br.com.percapita.utils.DataResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SignUpRepository(
    private val api: PercapitaApi = PercapitaApi.instance,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) {

    suspend fun signUp(user: User) = flow<DataResult<User>> {
        val dataApi = api.signUp(user)
        emit(DataResult.Success(dataApi))
    }.updateState().flowOn(dispatcher)

    companion object {
        val instance by lazy { SignUpRepository() }
    }
}