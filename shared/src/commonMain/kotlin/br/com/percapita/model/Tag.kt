package br.com.percapita.model

import kotlinx.serialization.Serializable

@Serializable
data class Tag(
    val tagId: String,
    val tagName: String
)