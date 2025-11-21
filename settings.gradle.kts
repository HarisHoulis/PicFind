pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    includeBuild("build-logic")
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "PicFind"

include(":app")
include(":core:coroutines")
include(":core:dispatcher")
include(":core:design")
include(":core:network")
include(":core:result")
include(":core:test")
include(":data:pictures:pictures-api")
include(":data:pictures:pictures-impl")
include(":ui:feature:details")
include(":ui:feature:list")
include(":ui:common:mvi")
include(":ui:common:navigation")
include(":ui:common:saved_state")
include(":ui:common:testutil")
