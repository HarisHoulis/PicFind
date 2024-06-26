plugins {
    id("picfind.android.feature")
    id("picfind.android.test")
}

android {
    namespace = "com.houlis.haris.picfind.feature.details"
}

dependencies {
    implementation(projects.core.coroutines)
    implementation(projects.data.pictures.picturesApi)
    implementation(projects.ui.common.mvi)
    implementation(projects.ui.common.navigation)
    implementation(projects.ui.common.savedState)

    testImplementation(projects.ui.common.testutil)
}
