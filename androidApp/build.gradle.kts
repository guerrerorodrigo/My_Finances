plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "com.rodrigoguerrero.myfinances.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.rodrigoguerrero.myfinances.android"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose)
    implementation(libs.bundles.compose.tooling)
    implementation(libs.io.insert.koin.android)
    implementation(libs.io.insert.koin.androidx.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.org.jetbrains.kotlinx.datetime)
}