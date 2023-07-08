import com.android.build.api.dsl.ApplicationExtension
import com.houlis.haris.pictrfindr.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidComposeApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.application")
            configureAndroidCompose(extensions.getByType<ApplicationExtension>())
        }
    }
}