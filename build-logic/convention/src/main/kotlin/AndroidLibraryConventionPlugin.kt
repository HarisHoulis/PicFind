import com.android.build.api.dsl.LibraryExtension
import com.houlis.haris.picfind.configureKotlinAndroid
import com.houlis.haris.picfind.configurePackagingResources
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

internal class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                configurePackagingResources()
                compileSdk = 34
            }

            dependencies {
                "testImplementation"(kotlin("test"))
                "androidTestImplementation"(kotlin("test"))
            }
        }
    }
}
