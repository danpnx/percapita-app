plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "br.com.percapita.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "br.com.percapita.android"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.0"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    val iconsVersion = "1.3.1"
    val navVersion = "2.5.3"
    val materialVersion = "1.0.1"

    implementation(project(":shared"))
    implementation("androidx.compose.ui:ui:1.2.1")
    implementation("androidx.compose.ui:ui-tooling:1.2.1")
    implementation("androidx.compose.ui:ui-tooling-preview:1.2.1")
    implementation("androidx.compose.foundation:foundation:1.2.1")
    implementation("androidx.compose.material:material:1.2.1")
    implementation("androidx.activity:activity-compose:1.5.1")
    implementation("androidx.compose.material:material-icons-extended:$iconsVersion")
    implementation("androidx.navigation:navigation-compose:$navVersion")
    implementation("androidx.compose.material3:material3:$materialVersion")
}