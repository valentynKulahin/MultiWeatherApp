package com.example.model.model.weather

data class ForecastExternalModel(
    val forecastday: List<ForecastdayExternalModel>? = listOf()
)