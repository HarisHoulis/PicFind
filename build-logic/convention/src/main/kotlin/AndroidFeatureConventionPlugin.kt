import com.android.build.api.dsl.LibraryExtension
import com.houlis.haris.picfind.configureAndroidCompose
import com.houlis.haris.picfind.configureKotlinAndroid
import com.houlis.haris.picfind.configurePackagingResources
import com.houlis.haris.picfind.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.project

internal class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("picfind.android.library")
                apply("picfind.android.hilt")
                apply(libs.findPlugin("org.jetbrains.kotlin.plugin.compose").get().get().pluginId)
            }

            extensions.configure<LibraryExtension> {
                configureAndroidCompose(this)
                configureKotlinAndroid(this)
                configurePackagingResources()
            }

            dependencies {
                "implementation"(project(":core:network"))
                "implementation"(project(":core:design"))
                "implementation"(libs.findLibrary("androidx.hilt.hilt.navigation.compose").get())
                "implementation"(libs.findLibrary("androidx.lifecycle.lifecycle.runtime.compose").get())
                "implementation"(libs.findLibrary("androidx.lifecycle.lifecycle.viewmodel.compose").get())
                "implementation"(libs.findLibrary("io.coil.kt").get())
                "implementation"(libs.findLibrary("io.coil.kt.coil.compose").get())
                "implementation"(libs.findLibrary("org.jetbrains.kotlinx.kotlinx.coroutines.android").get())

                "testImplementation"(project(":core:test"))
                "testImplementation"(kotlin("test"))

                "androidTestImplementation"(project(":core:test"))
                "androidTestImplementation"(kotlin("test"))
            }
        }
    }
}
