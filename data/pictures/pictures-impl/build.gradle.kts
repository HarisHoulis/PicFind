plugins {
    id("picfind.android.library")
    id("picfind.android.hilt")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    id("picfind.android.test")
    alias(libs.plugins.org.jetbrains.kotlin.plugin.serialization)
}

android {
    namespace = "com.houlis.haris.picfind.data.pictures.impl"

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
    implementation(projects.core.network)
    implementation(projects.core.result)
    implementation(projects.data.pictures.picturesApi)

    with(libs) {
        implementation(dev.forkhandles.result4k)
        implementation(androidx.datastore.datastore.preferences)
        implementation(org.jetbrains.kotlinx.kotlinx.coroutines.android)
        implementation(org.jetbrains.kotlinx.kotlinx.serialization.json)
        implementation(com.squareup.okhttp3.logging.interceptor)
        implementation(com.squareup.retrofit2.retrofit)
        implementation(com.jakewharton.retrofit.retrofit2.kotlinx.serialization.converter)

        testImplementation(projects.core.test)
    }
}
