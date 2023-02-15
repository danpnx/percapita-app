package br.com.percapita.model

import kotlinx.serialization.Serializable

@Serializable
data class Login(
    val username: String,
    private val password: String
)