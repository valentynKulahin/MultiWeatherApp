pluginManagement {
    includeBuild("build-logic")
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

rootProject.name = "multi_weather_app"
include(":app")

include(":core:domain")
include(":core:data")
include(":core:network")
include(":core:designsystem")
include(":core:database")
include(":core:common")
include(":core:datastore")
include(":core:notification")
include(":core:navi")

include(":feature:home")
include(":feature:search")
include(":feature:splash")
include(":feature:detail")
include(":feature:settings")
include(":feature:news")
include(":feature:location")
