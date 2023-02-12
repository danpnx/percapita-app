package br.com.percapita.api

import br.com.percapita.api.DefaultUrl.DEFAULT_URL
import br.com.percapita.model.FinancialTransaction
import br.com.percapita.model.Tag
import br.com.percapita.model.User
import br.com.percapita.payload.FinancialTransactionPayload
import br.com.percapita.payload.TagList
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import kotlin.native.concurrent.ThreadLocal

class PercapitaApi {

    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    useAlternativeNames = false
                }
            )
        }
    }

    @ThreadLocal
    companion object Instance {
        val instance by lazy { PercapitaApi() }
        var token = ""
    }

    suspend fun registerTag(): Tag {
        return httpClient.post("$DEFAULT_URL/tag/create").body()
    }

    suspend fun editTag(): Tag {
        return httpClient.put("$DEFAULT_URL/tag/edit/{id}").body()
    }

    suspend fun getTag(): Tag {
        return httpClient.get("$DEFAULT_URL/tag/{id}").body()
    }

    suspend fun getAllTags(): TagList {
        return httpClient.get("$DEFAULT_URL/tag/all").body()
    }

    suspend fun deleteTag(): Tag {
        return httpClient.delete("$DEFAULT_URL/tag/delete/{id}").body()
    }

    suspend fun signUp(): User {
        return httpClient.post("$DEFAULT_URL/signup").body()
    }

    suspend fun forgotPassword(): User {
        return httpClient.post("$DEFAULT_URL/forgot-password").body()
    }

    suspend fun resetPassword(): User {
        return httpClient.put("$DEFAULT_URL/reset-password").body()
    }

    suspend fun editName(): User {
        return httpClient.put("$DEFAULT_URL/user/edit-name").body()
    }

    suspend fun editPassword(): User {
        return httpClient.put("$DEFAULT_URL/user/edit-password").body()
    }

    suspend fun getProfile(): User {
        return httpClient.get("$DEFAULT_URL/user/profile").body()
    }

    suspend fun registerTransaction(): FinancialTransaction {
        return httpClient.post("$DEFAULT_URL/transaction/register").body()
    }

    suspend fun deleteTransaction(): FinancialTransaction {
        return httpClient.delete("$DEFAULT_URL/transaction/delete/{id}").body()
    }

    suspend fun getTransactionByCategory(): FinancialTransaction {
        return httpClient.get("$DEFAULT_URL/transaction/by-category").body()
    }

    suspend fun getTransactionById(): FinancialTransaction {
        return httpClient.get("$DEFAULT_URL/transaction/{id}").body()
    }

    suspend fun  getAllTransactions(): FinancialTransactionPayload {
        return httpClient.get("$DEFAULT_URL/transaction/all").body()
    }

    suspend fun editValue(): FinancialTransaction {
        return httpClient.put("$DEFAULT_URL/transaction/edit/value").body()
    }

    suspend fun editCategory(): FinancialTransaction {
        return httpClient.put("$DEFAULT_URL/transaction/edit/category").body()
    }

    suspend fun editDate(): FinancialTransaction {
        return httpClient.put("$DEFAULT_URL/transaction/edit/date").body()
    }

    suspend fun editDescription(): FinancialTransaction {
        return httpClient.put("$DEFAULT_URL/transaction/edit/description").body()
    }

    suspend fun changeTag(): FinancialTransaction {
        return httpClient.put("$DEFAULT_URL/transaction/edit/tag").body()
    }

    suspend fun findByTag(): FinancialTransaction {
        return httpClient.get("$DEFAULT_URL/transaction/by-tag").body()
    }

}
