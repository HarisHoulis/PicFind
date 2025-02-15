import com.android.build.api.dsl.ApplicationExtension
import com.houlis.haris.picfind.configureAndroidCompose
import com.houlis.haris.picfind.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

internal class AndroidComposeApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply(libs.findPlugin("org.jetbrains.kotlin.plugin.compose").get().get().pluginId)
            }
            configureAndroidCompose(extensions.getByType<ApplicationExtension>())
        }
    }
}
