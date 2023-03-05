package br.com.percapita.repository

import br.com.percapita.api.PercapitaApi
import br.com.percapita.extension.updateState
import br.com.percapita.model.EditUser
import br.com.percapita.model.User
import br.com.percapita.utils.DataResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ProfileRepository(
   private val api: PercapitaApi = PercapitaApi.instance,
   private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) {

    suspend fun getProfile() = flow<DataResult<User>> {
        val dataApi = api.getProfile()
        emit(DataResult.Success(dataApi))
    }.updateState().flowOn(dispatcher)

    suspend fun editName(user: User) = flow<DataResult<User>> {
        val dataApi = api.editName(user)
        emit(DataResult.Success(dataApi))
    }.updateState().flowOn(dispatcher)

    suspend fun editPassword(user: User) = flow<DataResult<User>> {
        val dataApi = api.editPassword(user)
        emit(DataResult.Success(dataApi))
    }.updateState().flowOn(dispatcher)

    suspend fun editUser(editUser: EditUser) = flow<DataResult<User>> {
        val dataApi = api.editUser(editUser)
        emit(DataResult.Success(dataApi))
    }.updateState().flowOn(dispatcher)

    companion object {
        val instance by lazy { ProfileRepository() }
    }

}