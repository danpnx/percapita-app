package br.com.percapita.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val username: String,
    val password: String,
    val name: String
) {
}

@Serializable
data class ProfileToken(
    val token: String
)

@Serializable
data class EditUser(
    val actualPassword: String,
    val newPassword: String,
    val newName: String
)