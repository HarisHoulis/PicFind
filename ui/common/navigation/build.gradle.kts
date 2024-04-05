plugins {
    id("picfind.android.library")
    id("picfind.android.test")
    id("picfind.kotlin.test")
    id("picfind.android.hilt")
}

android {
    namespace = "com.houlis.haris.picfind.ui.common.navigation"
}

dependencies {
    implementation(projects.core.coroutines)

    implementation(libs.androidx.lifecycle.lifecycle.viewmodel.ktx)
    implementation(libs.com.jakewharton.timber.timber)
    implementation(libs.bundles.compose)
}
