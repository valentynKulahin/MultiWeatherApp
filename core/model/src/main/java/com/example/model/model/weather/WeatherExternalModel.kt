package com.example.model.model.weather

data class WeatherExternalModel(
    val alerts: AlertsExternalModel? = AlertsExternalModel(),
    val current: CurrentExternalModel? = CurrentExternalModel(),
    val forecast: ForecastExternalModel? = ForecastExternalModel(),
    val location: LocationExternalModel? = LocationExternalModel()
)