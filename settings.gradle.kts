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
include(":features:my-courses")
include(":features:courses")
include(":features:menu")
include(":data:courses")
include(":features:login")
include(":data:menu")
include(":data:my-courses")
include(":data:login")
include(":core:theme")
