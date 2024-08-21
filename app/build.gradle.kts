import prodcontest.buildsrc.data
import prodcontest.buildsrc.appCompat
import prodcontest.buildsrc.auth
import prodcontest.buildsrc.test
import prodcontest.buildsrc.koin
import prodcontest.buildsrc.glide
import prodcontest.buildsrc.compose
import prodcontest.buildsrc.timber
import prodcontest.buildsrc.navigation
import prodcontest.buildsrc.domain
import prodcontest.buildsrc.maps
import prodcontest.buildsrc.paging

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "prodcontest.lifestylehub"
    compileSdk = 34

    defaultConfig {
        applicationId = "prodcontest.lifestylehub"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(libs.androidx.rules)
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    appCompat()
    test()
    koin()
    glide()
    compose()
    timber()
    navigation()
    maps()
    paging()

    domain()
    data()
    auth()
}