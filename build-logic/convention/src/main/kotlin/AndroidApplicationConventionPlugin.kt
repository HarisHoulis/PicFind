import com.android.build.api.dsl.ApplicationExtension
import com.houlis.haris.picfind.configureKotlinAndroid
import com.houlis.haris.picfind.configurePackagingResources
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                configurePackagingResources()
                defaultConfig.targetSdk = 34
            }
        }
    }
}
