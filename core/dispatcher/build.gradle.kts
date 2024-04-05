plugins {
    id("picfind.android.library")
    id("picfind.android.hilt")
}

android {
    namespace = "com.houlis.haris.picfind.core.dispatcher"
}

dependencies {
    implementation(libs.bundles.coroutines)
}
