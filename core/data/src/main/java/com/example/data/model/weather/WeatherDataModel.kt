package com.example.data.model.weather

data class WeatherDataModel(
    val alerts: AlertsDataModel? = AlertsDataModel(),
    val current: CurrentDataModel? = CurrentDataModel(),
    val forecast: ForecastDataModel? = ForecastDataModel(),
    val location: LocationDataModel? = LocationDataModel()
)