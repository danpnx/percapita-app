package br.com.percapita.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val username: String,
    val password: String,
    val name: String
)

@Serializable
class ProfileToken(val token: String) {
}
