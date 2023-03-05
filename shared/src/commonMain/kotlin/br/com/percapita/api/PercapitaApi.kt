package br.com.percapita.api

import br.com.percapita.api.DefaultUrl.DEFAULT_URL
import br.com.percapita.model.*
import br.com.percapita.payload.FinancialTransactionPayload
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
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

        defaultRequest {
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
            header("Authorization", "Bearer $token")
        }
    }

    @ThreadLocal
    companion object Instance {
        val instance by lazy { PercapitaApi() }
        var token = ""
    }

    suspend fun registerTag(tag: Tag): Tag {
        return httpClient.post("$DEFAULT_URL/tag/create") {
            setBody(tag)
        }.body()
    }

    suspend fun editTag(tag: Tag): Tag {
        return httpClient.put("$DEFAULT_URL/tag/edit/${tag.id}") {
            setBody(tag)
        }.body()
    }

    suspend fun getTag(): Tag {
        return httpClient.get("$DEFAULT_URL/tag/{id}").body()
    }

    suspend fun getAllTags(): List<Tag> {
        return httpClient.get("$DEFAULT_URL/tag/all").body()
    }

    suspend fun deleteTag(tag: Tag): Tag {
        return httpClient.delete("$DEFAULT_URL/tag/delete/${tag.id}").body()
    }

    suspend fun signUp(user: User): User {
        return httpClient.post("$DEFAULT_URL/signup") {
            setBody(user)
        }.body()
    }

    suspend fun forgotPassword(user: User): User {
        return httpClient.post("$DEFAULT_URL/forgot-password") {
            setBody(user)
        }.body()
    }

    suspend fun resetPassword(user: User): User {
        return httpClient.put("$DEFAULT_URL/reset-password") {
            setBody(user)
        }.body()
    }

    suspend fun editName(user: User): User {
        return httpClient.put("$DEFAULT_URL/user/edit-name") {
            setBody(user)
        }.body()
    }

    suspend fun editPassword(user: User): User {
        return httpClient.put("$DEFAULT_URL/user/edit-password") {
            setBody(user)
        }.body()
    }

    suspend fun getProfile(): User {
        return httpClient.get("$DEFAULT_URL/user/profile").body()
    }

    suspend fun registerTransaction(financialTransaction: FinancialTransaction): FinancialTransaction {
        return httpClient.post("$DEFAULT_URL/transaction/register") {
            setBody(financialTransaction)
        }.body()
    }

    suspend fun deleteTransaction(transactionId: String): FinancialTransaction {
        return httpClient.delete("$DEFAULT_URL/transaction/delete/${transactionId}").body()
    }

    suspend fun getTransactionByCategory(financialTransaction: FinancialTransaction): FinancialTransaction {
        return httpClient.get("$DEFAULT_URL/transaction/by-category").body()
    }

    suspend fun getTransactionById(transactionId: String): FinancialTransaction {
        return httpClient.get("$DEFAULT_URL/transaction/${transactionId}").body()
    }

    suspend fun  getAllTransactions(): List<FinancialTransaction> {
        return httpClient.get("$DEFAULT_URL/transaction/all").body()
    }

    suspend fun editValue(financialTransaction: FinancialTransaction): FinancialTransaction {
        return httpClient.put("$DEFAULT_URL/transaction/${financialTransaction.transactionId}/value") {
            setBody(financialTransaction)
        }.body()
    }

    suspend fun editCategory(financialTransaction: FinancialTransaction): FinancialTransaction {
        return httpClient.put("$DEFAULT_URL/transaction/${financialTransaction.transactionId}/category") {
            setBody(financialTransaction)
        }.body()
    }

    suspend fun editDate(financialTransaction: FinancialTransaction): FinancialTransaction {
        return httpClient.put("$DEFAULT_URL/transaction/${financialTransaction.transactionId}/date") {
            setBody(financialTransaction)
        }.body()
    }

    suspend fun editDescription(financialTransaction: FinancialTransaction): FinancialTransaction {
        return httpClient.put("$DEFAULT_URL/transaction/${financialTransaction.transactionId}/description") {
            setBody(financialTransaction)
        }.body()
    }

    suspend fun changeTag(financialTransaction: FinancialTransaction): FinancialTransaction {
        return httpClient.put("$DEFAULT_URL/transaction/${financialTransaction.transactionId}/tag") {
            setBody(financialTransaction)
        }.body()
    }

    suspend fun findByTag(): FinancialTransaction {
        return httpClient.get("$DEFAULT_URL/transaction/by-tag").body()
    }

    suspend fun editTransaction(financialTransaction: FinancialTransaction): FinancialTransaction {
        return httpClient.put("$DEFAULT_URL/transaction/edit/${financialTransaction.transactionId}") {
            setBody(financialTransaction)
        }.body()
    }

    suspend fun login(login: Login): ProfileToken {
        val response: HttpResponse = httpClient.post("$DEFAULT_URL/login") {
            setBody(login)
        }.body()
        if(response.status.value in 200..299) {
            val authorization = response.headers["Authorization"]
            val authorizationBearer = authorization.toString().replace(login.username, "Bearer")
            return ProfileToken(authorizationBearer)
        } else {
            return response.body()
        }
    }

    suspend fun report(): Report {
        return httpClient.get("$DEFAULT_URL/report").body()
    }

    suspend fun editUser(editUser: EditUser): User {
        return httpClient.put("$DEFAULT_URL/user/edit-user") {
            setBody(editUser)
        }.body()
    }
}
