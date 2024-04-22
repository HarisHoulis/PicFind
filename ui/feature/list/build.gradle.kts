plugins {
    id("picfind.android.feature")
    id("picfind.android.test")
}

android {
    namespace = "com.houlis.haris.picfind.feature.list"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(projects.core.coroutines)
    implementation(projects.core.design)
    implementation(projects.data.pictures.picturesApi)
    implementation(projects.ui.common.mvi)
    implementation(projects.ui.common.navigation)
    implementation(projects.ui.common.savedState)

    implementation(libs.dev.forkhandles.result4k)

    testImplementation(projects.ui.common.testutil)
}
