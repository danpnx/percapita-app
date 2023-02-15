package br.com.percapita.model

import kotlinx.serialization.Serializable

@Serializable
data class Report(
    val summaryData: Map<String, Double>,
    val chartData: Map<String, Double>
)
