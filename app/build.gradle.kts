import com.asarkar.gradle.buildtimetracker.BarPosition
import com.asarkar.gradle.buildtimetracker.Output
import com.asarkar.gradle.buildtimetracker.Sort
import groovy.json.JsonSlurper
import java.net.URL
import java.time.Duration

plugins {
    id("android-app-module")
    alias(libs.plugins.graph)
    alias(libs.plugins.time.tracker)
    id("com.spotify.ruler")
}

android {
    defaultConfig {
        applicationId = Const.NAMESPACE
        versionCode = 1
        versionName = "1.0"
        targetSdk = Const.COMPILE_SKD
    }
}

dependencies {

    implementation(project(":feature1"))
    implementation(project(":feature2"))
    implementation(project(":feature3"))

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

buildTimeTracker {
    barPosition = BarPosition.TRAILING
    sortBy = Sort.ASC
    output = Output.CSV
    minTaskDuration = Duration.ofSeconds(1)
    reportsDir.set(File(layout.buildDirectory.get().asFile, "reports/buildTimeTracker"))
}

ruler {
    abi.set("arm64-v8a")
    locale.set("en")
    screenDensity.set(480)
    sdkVersion.set(27)
}

tasks.register("getYourDog") {
    doLast {
        val input = URL("https://dog.ceo/api/breeds/image/random").openConnection().apply {
            doInput = true
            connect()
        }.inputStream
        val json = JsonSlurper().parse(input) as Map<String, String>
        println("Your dog is ${json["message"]}")
    }
}
