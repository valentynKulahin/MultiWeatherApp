package com.example.network.repos.weather

import com.example.common.ApiResult

interface WeatherNetworkRepo {

    suspend fun getForecastWeather(
        country: String,
        days: Int,
        aqi: String,
        alerts: String
    ): ApiResult

    suspend fun getForecastWeatherByLatLon(
        latLon: String,
        days: Int,
        aqi: String,
        alerts: String
    ): ApiResult

}