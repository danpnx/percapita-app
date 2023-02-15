package br.com.percapita.extension

import br.com.percapita.utils.DataResult
import io.ktor.utils.io.errors.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.retryWhen

fun <T : Any> Flow<DataResult<T>>.updateState() =
    retryWhen { cause, attempt ->
        if (cause is IOException && attempt < 3) {
            delay(2000)
            true
        } else {
            false
        }
    }
        .onStart { emit(DataResult.Loading) }
        .catch { emit(DataResult.Error(it)) }