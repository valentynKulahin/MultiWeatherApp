package com.example.data.model.weather

data class ForecastDataModel(
    val forecastday: List<ForecastdayDataModel>? = listOf()
)