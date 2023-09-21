package com.example.domain.model.current

data class CurrentWeather(
    val location: Location? = Location(),
    val current: Current? = Current()
)