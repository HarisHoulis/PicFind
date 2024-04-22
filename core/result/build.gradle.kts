plugins {
    id("picfind.android.library")
}

android {
    namespace = "com.houlis.haris.picfind.core.result"
}

dependencies {
    implementation(libs.dev.forkhandles.result4k)
}
