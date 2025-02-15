buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }

}

plugins {
    alias(libs.plugins.com.android.application) apply false
    alias(libs.plugins.com.google.android.libraries.mapsplatform.secrets.gradle.plugin) apply false
    alias(libs.plugins.com.google.dagger.hilt.android) apply false
    alias(libs.plugins.org.jetbrains.kotlin.jvm) apply false
    alias(libs.plugins.org.jetbrains.kotlin.plugin.compose) apply false
    alias(libs.plugins.org.jetbrains.kotlin.plugin.serialization) apply false
}

tasks.register<Copy>("installGitHooks") {
    from(layout.projectDirectory.dir("scripts/hooks"))
    into(File(rootDir, "/.git/hooks"))
    fileMode = 775
}
