pluginManagement {
    repositories {
        google()
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

rootProject.name = "ZarinaCapital"
include(":app")
include(":core:data")
include(":core:common")
include(":core:presentation")
include(":features:sign-up")
include(":features:my-courses")
include(":features:courses")
include(":features:menu")
include(":data:courses")
include(":features:sign-in")
include(":data:menu")
include(":data:my-courses")
include(":data:sign-in")
include(":data:sign-up")
include(":core:theme")
