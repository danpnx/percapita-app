package net.azurewebsites.percapita

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform