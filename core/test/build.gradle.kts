plugins {
    id("picfind.android.library")
    id("picfind.android.hilt")
    alias(libs.plugins.org.jetbrains.kotlin.plugin.serialization)
}

android {
    namespace = "com.houlis.haris.picfind.core.test"

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

dependencies {
    implementation(projects.core.network)
    implementation(projects.data.pictures.picturesApi)

    implementation(libs.org.jetbrains.kotlinx.kotlinx.coroutines.test)
    implementation(libs.org.jetbrains.kotlinx.kotlinx.serialization.json)
    implementation(libs.dev.forkhandles.result4k)

}
