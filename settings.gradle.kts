pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "notes-shift-lab"
include(":app")
include(":di")
include(":ui-kit")
include(":navigation")
include(":features:main:main-ui")
include(":features:main:main-logic")
include(":features:detail:detail-ui")
include(":features:detail:detail-logic")
include("features:settings:settings-ui")
include("features:settings:settings-logic")
include(":core:notes-data")
include(":core:notes-database")
include(":settings")
