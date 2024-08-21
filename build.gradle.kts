import prodcontest.buildsrc.Versions

// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("app.cash.paparazzi:paparazzi-gradle-plugin:1.3.0")
    }
}

plugins {
    id("com.android.application") version "8.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("org.jetbrains.kotlin.jvm") version prodcontest.buildsrc.Versions.kotlin apply false
    id("com.android.library") version "8.1.2" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.21"
}