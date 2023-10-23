package com.example.domain.model.weather

data class ForecastDomainModel(
    val forecastday: List<ForecastdayDomainModel>? = listOf()
)