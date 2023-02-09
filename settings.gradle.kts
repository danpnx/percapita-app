pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven (url =  "https://jitpack.io")
    }
}

rootProject.name = "PerCapita"
include(":androidApp")
include(":shared")