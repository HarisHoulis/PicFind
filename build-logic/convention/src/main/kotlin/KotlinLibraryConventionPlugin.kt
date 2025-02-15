import com.houlis.haris.picfind.configureJavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.configure

internal class KotlinLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("java-library")
                apply("org.jetbrains.kotlin.jvm")
                apply("com.android.lint")
            }
            extensions.configure<JavaPluginExtension> {
                configureJavaVersion(target)
            }
        }
    }
}
