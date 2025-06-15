plugins {
    id("android-core-module")
}

dependencies {

    api(project(":model"))

    api(libs.retrofit)
    api(libs.retrofit.converter.gson)
    api(libs.gson)
}