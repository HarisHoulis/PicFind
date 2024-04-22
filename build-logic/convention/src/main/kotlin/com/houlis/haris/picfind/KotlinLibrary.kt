package com.houlis.haris.picfind

import org.gradle.api.plugins.JavaPluginExtension

internal fun configureKotlinLibrary(
    javaPluginExtension: JavaPluginExtension,
) {
    javaPluginExtension.apply {
        sourceCompatibility = PREFERRED_JAVA_VERSION
        targetCompatibility = PREFERRED_JAVA_VERSION
    }
}
