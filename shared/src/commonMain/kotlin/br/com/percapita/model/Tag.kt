package br.com.percapita.model

import kotlinx.serialization.Serializable

@Serializable
data class Tag(
    val id: String,
    val tagName: String
)