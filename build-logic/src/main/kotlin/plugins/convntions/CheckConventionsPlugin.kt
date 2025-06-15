package plugins.convntions

import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project

class CheckConventionsPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.rootProject.allprojects.forEach { childProject ->
            childProject.plugins.withId("com.android.library") {
                when {
                    childProject.path.startsWith(":core:") -> {
                        if (!childProject.pluginManager.hasPlugin("android-core-module")) {
                            throw GradleException("Plugin \"android-core-module\" required for core module")
                        }
                    }
                    childProject.path.startsWith(":features:") -> {
                        if (!childProject.pluginManager.hasPlugin("android-feature-module")) {
                            throw GradleException("Plugin \"android-feature-module\" required for feature module")
                        }
                    }
                }
            }
            childProject.plugins.withId("com.android.application") {
                if (!childProject.pluginManager.hasPlugin("android-app-module")) {
                    throw GradleException("Plugin \"android-app-module\" required for app module")
                }
            }
        }
    }
}