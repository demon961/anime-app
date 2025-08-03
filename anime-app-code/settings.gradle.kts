pluginManagement {
    plugins {
        id("com.android.application") version "7.0.4"
        kotlin("android") version "1.6.21"
    }
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "anime-app-code"
include(":app")
