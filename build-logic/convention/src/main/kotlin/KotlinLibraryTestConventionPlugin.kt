import com.android.build.gradle.internal.tasks.factory.dependsOn
import com.houlis.haris.picfind.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.named

internal class KotlinLibraryTestConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                add("testImplementation", platform(libs.findLibrary("org.junit.junit.bom").get()))
                add("testImplementation", kotlin("test"))
                add("testImplementation", libs.findLibrary("androidx.compose.compose.bom").get())
                add("testImplementation", libs.findBundle("test").get())
                add("testRuntimeOnly", libs.findLibrary("org-junit-vintage-junit-vintage-engine").get())
            }

            tasks.named<Test>("test") {
                useJUnitPlatform()

                testLogging {
                    events("passed", "skipped", "failed")
                    showStandardStreams = true
                    showStackTraces = true
                    showCauses = true
                    exceptionFormat = TestExceptionFormat.FULL
                }
            }

            tasks.register("testDebugUnitTest")
                .dependsOn("test")
        }
    }
}
