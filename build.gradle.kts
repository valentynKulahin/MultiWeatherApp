import org.gradle.internal.impldep.org.junit.experimental.categories.Categories.CategoryFilter.exclude

// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    alias(libs.plugins.com.android.application) apply false
    alias(libs.plugins.org.jetbrains.kotlin.android) apply false
    alias(libs.plugins.com.android.library) apply false
    alias(libs.plugins.org.jetbrains.kotlin.jvm) apply false
    alias(libs.plugins.com.google.hilt) apply false
    alias(libs.plugins.com.google.ksp) apply false
    alias(libs.plugins.com.google.sgp) apply false
}
