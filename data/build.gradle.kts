import prodcontest.buildsrc.appCompat
import prodcontest.buildsrc.coroutines
import prodcontest.buildsrc.domain
import prodcontest.buildsrc.glide
import prodcontest.buildsrc.koin
import prodcontest.buildsrc.location
import prodcontest.buildsrc.test
import prodcontest.buildsrc.timber
import prodcontest.buildsrc.network

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("plugin.serialization") version "1.8.21"
    id("org.jetbrains.kotlin.kapt")
}


android {
    namespace = "prodcontest.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation(libs.core.ktx)
    implementation(libs.androidx.rules)

    val room_version = "2.6.1"

    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")

    // To use Kotlin annotation processing tool (kapt)
    kapt("androidx.room:room-compiler:$room_version")
    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$room_version")

    appCompat()
    test()
    koin()
    glide()
    timber()
    domain()
    network()
    coroutines()
    location()
}