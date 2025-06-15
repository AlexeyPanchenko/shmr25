@file:Suppress("UnstableApiUsage")

includeBuild("build-logic")

pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Shmr25"
include(":app")

listOf(
    "model",
    "core",
    "network",
).forEach {
    include(":$it")
    project(":$it").projectDir = file("core/$it")
}

listOf(
    "feature1",
    "feature2",
    "feature3",
).forEach {
    include(":$it")
    project(":$it").projectDir = file("features/$it")
}

