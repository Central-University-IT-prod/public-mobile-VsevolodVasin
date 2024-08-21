plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("plugin.serialization") version "1.8.21"
    id("org.jetbrains.kotlin.kapt")
}


android {
    namespace = "prodcontest.auth.data"
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
}
object Versions {

    // Versions for project's build.gradle.kts
    const val kotlin = "1.9.0"

    // Versions for module's build.gradle.kts
    const val kotlinCompilerExtensionVersion = "1.4.4"

    const val COMPOSE_BOM_VERSION = "2023.03.00"
    const val KOIN_VERSION = "3.3.2"
    const val KOIN_EXT_VERSION = "3.0.2"
    const val KOIN_COMPOSE_VERSION = "3.4.1"
    const val NAV_VERSION = "2.5.3"
    const val COROUTINES_VERSION = "1.6.4"
    const val KTOR_VERSION = "2.3.9"
    const val LANDSCAPIST_GLIDE = "2.3.2"
    const val TIMBER = "4.7.1"
    const val JUNIT = "4.13.2"
    const val LOCATION = "21.2.0"

    object Androidx {
        const val APP_COMPAT = "1.5.1"
        const val CORE_KTX = "1.9.0"
        const val RUNTIME_COMPOSE = "2.6.0-alpha05"
        const val TEST_EXT = "1.1.5"
        const val TEST_ESPRESSO = "3.5.1"
    }
}

object Dependencies {
    const val KOIN_CORE = "io.insert-koin:koin-core:${Versions.KOIN_VERSION}"
    const val KOIN_ANDROID = "io.insert-koin:koin-android:${Versions.KOIN_VERSION}"
    const val KOIN_ANDROID_EXT = "io.insert-koin:koin-android-ext:${Versions.KOIN_EXT_VERSION}"
    const val KOIN_COMPOSE = "io.insert-koin:koin-androidx-compose:${Versions.KOIN_COMPOSE_VERSION}"
}


fun DependencyHandler.koin() {
    implementation(prodcontest.buildsrc.Dependencies.KOIN_CORE)
    implementation(prodcontest.buildsrc.Dependencies.KOIN_ANDROID)
    implementation(prodcontest.buildsrc.Dependencies.KOIN_ANDROID_EXT)
    implementation(prodcontest.buildsrc.Dependencies.KOIN_COMPOSE)
}

fun DependencyHandler.coroutines() {
    implementation(prodcontest.buildsrc.Dependencies.KOTLINX_COROUTINES_ANDROID)
    implementation(prodcontest.buildsrc.Dependencies.COROUTINES_CORE)
}

fun DependencyHandler.network() {
    implementation(prodcontest.buildsrc.Dependencies.KTOR_CORE)
    implementation(prodcontest.buildsrc.Dependencies.KTOR_CLIENT)
    implementation(prodcontest.buildsrc.Dependencies.KTOR_SERIALIZATION)
    implementation(prodcontest.buildsrc.Dependencies.KTOR_LOGGING)
    implementation(prodcontest.buildsrc.Dependencies.KTOR_CONTENT_NEGOTIATION)
}


fun DependencyHandler.timber() {
    implementation(prodcontest.buildsrc.Dependencies.TIMBER)
}

fun DependencyHandler.test() {
    testImplementation(prodcontest.buildsrc.Dependencies.JUNIT4)
    androidTestImplementation(prodcontest.buildsrc.Dependencies.ANDROIDX_TEST_EXT_JUNIT)
    androidTestImplementation(prodcontest.buildsrc.Dependencies.ANDROIDX_TEST_ESPRESSO)
}

fun DependencyHandler.implementation(dependency: Any) {
    add("implementation", dependency)
}

fun DependencyHandler.debugImplementation(dependency: Any) {
    add("debugImplementation", dependency)
}

fun DependencyHandler.testImplementation(dependency: Any) {
    add("testImplementation", dependency)
}

fun DependencyHandler.domain() {
    implementation(project(":auth:domain"))
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")


    val room_version = "2.6.1"

    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")

    // To use Kotlin annotation processing tool (kapt)
    kapt("androidx.room:room-compiler:$room_version")
    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$room_version")

    test()
    koin()
    timber()
    domain()
    network()
    coroutines()

}