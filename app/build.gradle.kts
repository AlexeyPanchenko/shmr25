import groovy.json.JsonSlurper
import java.net.URL

plugins {
    id("android-app-module")
    alias(libs.plugins.graph)
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
