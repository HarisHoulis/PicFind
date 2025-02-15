plugins {
    `kotlin-dsl`
}

group = "com.houlis.haris.picfind.buildlogic"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(libs.versions.jdk.version.get().toInt()))
    }
}

dependencies {
    compileOnly(libs.com.android.tools.build.gradle)
    compileOnly(libs.org.jetbrains.kotlin.kotlin.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "picfind.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidComposeApplication") {
            id = "picfind.android.compose.application"
            implementationClass = "AndroidComposeApplicationConventionPlugin"
        }
        register("androidFeature") {
            id = "picfind.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
        register("androidHIlt") {
            id = "picfind.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
        register("androidLibrary") {
            id = "picfind.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidTest") {
            id = "picfind.android.test"
            implementationClass = "AndroidTestConventionPlugin"
        }
        register("kotlinLibraryTest") {
            id = "picfind.kotlin.library.test"
            implementationClass = "KotlinLibraryTestConventionPlugin"
        }
        register("kotlinLibrary") {
            id = "picfind.kotlin.library"
            implementationClass = "KotlinLibraryConventionPlugin"
        }
    }
}
