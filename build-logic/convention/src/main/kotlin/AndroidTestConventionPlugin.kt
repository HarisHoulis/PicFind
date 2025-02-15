import com.houlis.haris.picfind.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

internal class AndroidTestConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.android")
            }

            dependencies {
                add("debugImplementation", platform(libs.findLibrary("androidx.compose.compose.bom").get()))
                add("debugImplementation", libs.findLibrary("androidx-compose-ui-ui-test-manifest").get())

                add("testImplementation", platform(libs.findLibrary("org.junit.junit.bom").get()))
                add("testImplementation", platform(libs.findLibrary("androidx.compose.compose.bom").get()))
                add("testImplementation", libs.findBundle("android-test").get())

                add("testRuntimeOnly", libs.findLibrary("org-junit-vintage-junit-vintage-engine").get())
                add("androidTestImplementation", kotlin("test"))
                add("androidTestImplementation", platform(libs.findLibrary("org.junit.junit.bom").get()))
                add("androidTestImplementation", platform(libs.findLibrary("androidx.compose.compose.bom").get()))
                add("androidTestImplementation", libs.findBundle("android-test").get())
            }
        }
    }
}
