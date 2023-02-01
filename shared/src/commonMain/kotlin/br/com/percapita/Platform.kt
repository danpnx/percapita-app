package br.com.percapita

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform