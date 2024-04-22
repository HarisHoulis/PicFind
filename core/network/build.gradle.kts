plugins {
    id("picfind.android.library")
    id("picfind.android.hilt")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    id("picfind.android.test")
    alias(libs.plugins.org.jetbrains.kotlin.plugin.serialization)
}

android {
    namespace = "com.houlis.haris.picfind.core.network"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt")
            )
        }
    }
}

secrets {
    defaultPropertiesFileName = "secrets.defaults.properties"
}

dependencies {
    implementation(libs.dev.forkhandles.result4k)
    implementation(libs.com.squareup.retrofit2.retrofit)
}
