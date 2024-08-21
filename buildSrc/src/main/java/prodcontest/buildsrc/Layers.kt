package prodcontest.buildsrc

import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

fun DependencyHandler.domain() {
    implementation(project(":domain"))
}

fun DependencyHandler.data() {
    implementation(project(":data"))
}

fun DependencyHandler.auth() {
    implementation(project(":auth:domain"))
    implementation(project(":auth:data"))
}