plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "prodcontest.auth"
    compileSdk = 34
}


dependencies {
    implementation(project(":auth:data"))
    implementation(project(":auth:domain"))
}



