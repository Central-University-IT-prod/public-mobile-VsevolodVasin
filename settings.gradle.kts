pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_PROJECT)
    repositories {
        google()
        mavenCentral()
    }
}


rootProject.name = "LifestyleHUB"
include(":app")
include(":data")
include(":domain")
include(":auth")
include(":auth:domain")
include(":auth:data")
include(":auth:buildSrc")
