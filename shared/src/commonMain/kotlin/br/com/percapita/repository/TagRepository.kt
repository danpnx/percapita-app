package br.com.percapita.repository

import br.com.percapita.api.PercapitaApi
import br.com.percapita.extension.updateState
import br.com.percapita.model.Tag
import br.com.percapita.payload.TagList
import br.com.percapita.utils.DataResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class TagRepository(
    private val api: PercapitaApi = PercapitaApi.instance,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) {

    suspend fun getAllTags() = flow<DataResult<TagList>> {
        emit(DataResult.Success(api.getAllTags()))
    }.updateState().flowOn(dispatcher)

    suspend fun registerTag(tag: Tag) = flow<DataResult<Tag>> {
        val dataApi = api.registerTag(tag)
        emit(DataResult.Success(dataApi))
    }.updateState().flowOn(dispatcher)

    companion object {
        val instance by lazy { TagRepository() }
    }
}