package com.example.domain.model.current

data class CurrentWeather(
    val current: Current? = Current(),
    val location: Location? = Location()
)