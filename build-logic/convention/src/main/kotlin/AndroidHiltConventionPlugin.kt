import com.houlis.haris.picfind.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal class AndroidHiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.dagger.hilt.android")
                // KAPT must go last to avoid build warnings.
                // See: https://stackoverflow.com/questions/70550883/warning-the-following-options-were-not-recognized-by-any-processor-dagger-f
                apply("org.jetbrains.kotlin.kapt")
            }

            dependencies {
                "implementation"(libs.findLibrary("com.google.dagger.hilt.android").get())
                "kapt"(libs.findLibrary("com.google.dagger.hilt.android.compiler").get())
                "kaptAndroidTest"(libs.findLibrary("com.google.dagger.hilt.android.compiler").get())
            }
        }
    }
}
