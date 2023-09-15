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

rootProject.name = "MultiWeatherApp"
include(":app")
include(":core:presentation")
include(":core:domain")
include(":core:data")
include(":core:network")
include(":feature:home")
include(":core:designsystem")
include(":core:database")
include(":core:common")
include(":feature:search")
include(":feature:splash")
include(":feature:detail")
include(":feature:settings")
include(":feature:news")
include(":feature:mylocation")
include(":core:datastore")
