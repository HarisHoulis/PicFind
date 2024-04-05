plugins {
    id("picfind.android.library")
    id("picfind.android.hilt")
}

android {
    namespace = "com.houlis.haris.picfind.core.coroutines"
}

dependencies {
    implementation(projects.core.dispatcher)

    implementation(libs.bundles.coroutines)
}
