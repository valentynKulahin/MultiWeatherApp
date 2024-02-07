import com.android.build.gradle.LibraryExtension
import com.example.multi_weather_app.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidFeaturePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("com.android.library")
                apply("com.google.hilt")
            }
            extensions.configure<LibraryExtension> {
                defaultConfig {
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }

                dependencies {
                    add("implementation", project(":core:designsystem"))

                    add("implementation", libs.findLibrary("androidx.hilt.navigation").get())
                    add("implementation", libs.findLibrary("androidx.lifecycle.runtime.compose").get())
                    add("implementation", libs.findLibrary("androidx.lifecycle.viewmodel.compose").get())
                }
            }
        }
    }
}