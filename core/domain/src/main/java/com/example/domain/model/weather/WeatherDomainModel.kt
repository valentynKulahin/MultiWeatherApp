package com.example.domain.model.weather

data class WeatherDomainModel(
    val alerts: AlertsDomainModel? = AlertsDomainModel(),
    val current: CurrentDomainModel? = CurrentDomainModel(),
    val forecast: ForecastDomainModel? = ForecastDomainModel(),
    val location: LocationDomainModel? = LocationDomainModel()
)