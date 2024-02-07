package com.example.multi_weather_app

enum class WeatherBuildType(val applicationIdSuffix: String? = null) {
    DEBUG(".debug"),
    RELEASE
}