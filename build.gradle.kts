import io.gitlab.arturbosch.detekt.Detekt

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
    alias(libs.plugins.graph) apply false
    alias(libs.plugins.time.tracker) apply false
    alias(libs.plugins.detekt)
}

buildscript {
    dependencies {
        classpath(libs.ruler.plugin)
    }
}

detekt {
    toolVersion = libs.versions.detekt.get()
    config.setFrom(file(File(rootDir, "config/detekt/detekt.yml")))
    buildUponDefaultConfig = true
}
tasks.withType<Detekt>().configureEach {
    reports {
        xml.required.set(true)
        html.required.set(true)
        sarif.required.set(true)
        md.required.set(true)
    }
    parallel = true
    autoCorrect = false
    basePath = rootProject.projectDir.absolutePath

    setSource(files(projectDir))
    include("**/*.kt")
    include("**/*.kts")
    exclude("**/resources/**")
    exclude("**/build/**")
}
