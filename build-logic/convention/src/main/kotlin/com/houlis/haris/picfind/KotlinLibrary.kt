package com.houlis.haris.picfind

import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.jvm.toolchain.JavaLanguageVersion

internal fun JavaPluginExtension.configureJavaVersion(project: Project) {
    apply {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(project.libs.findVersion("jdk-version").get().toString().toInt()))
        }
    }
}
