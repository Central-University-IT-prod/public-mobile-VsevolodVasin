package prodcontest.buildsrc
import org.gradle.api.artifacts.dsl.DependencyHandler
import prodcontest.buildsrc.Versions.Androidx

object Dependencies {
    val TIMBER by lazy { "com.jakewharton.timber:timber:${Versions.TIMBER}" }
    val kotlin by lazy { "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}" }


    // APP_COMPAT
    const val ANDROID_APP_COMPAT = "androidx.appcompat:appcompat:${Androidx.APP_COMPAT}"
    const val ANDROID_CORE_KTX = "androidx.core:core-ktx:${Androidx.CORE_KTX}"

    // COMPOSE
    // Compose: BOM
    const val COMPOSE_BOM = "androidx.compose:compose-bom:${Versions.COMPOSE_BOM_VERSION}"

    // Compose: Compose for Activity
    val COMPOSE_ACTIVITY by lazy { "androidx.activity:activity-compose:1.8.2" }

    // Compose: Compose for Lifecycle
    const val COMPOSE_RUNTIME_LIFECYCLE =
        "androidx.lifecycle:lifecycle-runtime-compose:${Androidx.RUNTIME_COMPOSE}"

    // Compose: Material
    const val COMPOSE_MATERIAL3 = "androidx.compose.material3:material3:1.2.1"
    const val COMPOSE_MATERIAL = "androidx.compose.material:material:1.6.3"

    // Compose: Android Studio Preview support
    const val COMPOSE_UI_TOOLING_PREVIEW = "androidx.compose.ui:ui-tooling-preview"
    const val COMPOSE_UI_TOOLING = "androidx.compose.ui:ui-tooling"

    // Compose: UI tests
    const val COMPOSE_UI_TEST_JUNIT4 = "androidx.compose.ui:ui-test-junit4"
    const val COMPOSE_UI_TEST_MANIFEST = "androidx.compose.ui:ui-test-manifest"

    // NAVIGATION COMPONENT
    // Navigation: Jetpack Compose Integration
    const val NAVIGATION_COMPOSE = "androidx.navigation:navigation-compose:${Versions.NAV_VERSION}"

    // KOIN
    const val KOIN_CORE = "io.insert-koin:koin-core:${Versions.KOIN_VERSION}"
    const val KOIN_ANDROID = "io.insert-koin:koin-android:${Versions.KOIN_VERSION}"
    const val KOIN_ANDROID_EXT = "io.insert-koin:koin-android-ext:${Versions.KOIN_EXT_VERSION}"
    const val KOIN_COMPOSE = "io.insert-koin:koin-androidx-compose:${Versions.KOIN_COMPOSE_VERSION}"

    // COROUTINES
    const val COROUTINES_CORE =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINES_VERSION}"
    const val KOTLINX_COROUTINES_ANDROID =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINES_VERSION}"

    // KTOR
    const val KTOR_CORE = "io.ktor:ktor-client-core:${Versions.KTOR_VERSION}"
    const val KTOR_CLIENT =
        "io.ktor:ktor-client-android:${Versions.KTOR_VERSION}"
    const val KTOR_SERIALIZATION =
        "io.ktor:ktor-serialization-kotlinx-json:${Versions.KTOR_VERSION}"
    const val KTOR_CONTENT_NEGOTIATION =
        "io.ktor:ktor-client-content-negotiation:${Versions.KTOR_VERSION}"
    const val KTOR_LOGGING =
        "io.ktor:ktor-client-logging:${Versions.KTOR_VERSION}"

    // GLIDE
    const val LANDSCAPIST_GLIDE =
        "com.github.skydoves:landscapist-glide:${Versions.LANDSCAPIST_GLIDE}"

    // GLIDE
    const val LOCATION =
        "com.google.android.gms:play-services-location:${Versions.LOCATION}"

    // TEST
    const val JUNIT4 = "junit:junit:${Versions.JUNIT}"
    const val ANDROIDX_TEST_EXT_JUNIT = "androidx.test.ext:junit:${Androidx.TEST_EXT}"
    const val ANDROIDX_TEST_ESPRESSO = "androidx.test.espresso:espresso-core:${Androidx.TEST_ESPRESSO}"

    const val YANDEX_MAPS = "com.yandex.android:maps.mobile:${Versions.YANDEX_MAPS}"
    const val PAGING_RUNTIME = "androidx.paging:paging-runtime:${Versions.PAGING}"
    const val PAGING_COMPOSE = "androidx.paging:paging-compose:${Versions.PAGING}"
}

fun DependencyHandler.appCompat() {
    implementation(Dependencies.ANDROID_APP_COMPAT)
    implementation(Dependencies.ANDROID_CORE_KTX)
}

fun DependencyHandler.compose() {
    val composeBom = platform(Dependencies.COMPOSE_BOM)
    implementation(composeBom)
    androidTestImplementation(composeBom)

    implementation(Dependencies.COMPOSE_ACTIVITY)
    implementation(Dependencies.COMPOSE_MATERIAL)
    implementation(Dependencies.COMPOSE_MATERIAL3)
    implementation(Dependencies.COMPOSE_UI_TOOLING_PREVIEW)
    implementation(Dependencies.COMPOSE_RUNTIME_LIFECYCLE)
    debugImplementation(Dependencies.COMPOSE_UI_TOOLING)
    androidTestImplementation(Dependencies.COMPOSE_UI_TEST_JUNIT4)
    debugImplementation(Dependencies.COMPOSE_UI_TEST_MANIFEST)
}

fun DependencyHandler.navigation() {
    implementation(Dependencies.NAVIGATION_COMPOSE)
}

fun DependencyHandler.maps() {
    implementation(Dependencies.YANDEX_MAPS)
}

fun DependencyHandler.paging() {
    implementation(Dependencies.PAGING_RUNTIME)
    implementation(Dependencies.PAGING_COMPOSE)
}

fun DependencyHandler.koin() {
    implementation(Dependencies.KOIN_CORE)
    implementation(Dependencies.KOIN_ANDROID)
    implementation(Dependencies.KOIN_ANDROID_EXT)
    implementation(Dependencies.KOIN_COMPOSE)
}

fun DependencyHandler.location() {
    implementation(Dependencies.LOCATION)
}

fun DependencyHandler.coroutines() {
    implementation(Dependencies.KOTLINX_COROUTINES_ANDROID)
    implementation(Dependencies.COROUTINES_CORE)
}

fun DependencyHandler.network() {
    implementation(Dependencies.KTOR_CORE)
    implementation(Dependencies.KTOR_CLIENT)
    implementation(Dependencies.KTOR_SERIALIZATION)
    implementation(Dependencies.KTOR_LOGGING)
    implementation(Dependencies.KTOR_CONTENT_NEGOTIATION)
}


fun DependencyHandler.glide() {
    implementation(Dependencies.LANDSCAPIST_GLIDE)
}

fun DependencyHandler.timber() {
    implementation(Dependencies.TIMBER)
}

fun DependencyHandler.test() {
    testImplementation(Dependencies.JUNIT4)
    androidTestImplementation(Dependencies.ANDROIDX_TEST_EXT_JUNIT)
    androidTestImplementation(Dependencies.ANDROIDX_TEST_ESPRESSO)
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

fun DependencyHandler.androidTestImplementation(dependency: Any) {
    add("androidTestImplementation", dependency)
}