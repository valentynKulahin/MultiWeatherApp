package com.example.network.repos.weather

import com.example.network.utils.NetworkError
import com.example.network.utils.NetworkResponse
import com.example.network.models.weather.WeatherNetworkModel

interface WeatherNetworkRepo {

    suspend fun getForecastWeather(
        country: String,
        days: Int,
        aqi: String,
        alerts: String
    ): NetworkResponse<WeatherNetworkModel, NetworkError>

    suspend fun getForecastWeatherByLatLon(
        latLon: String,
        days: Int,
        aqi: String,
        alerts: String
    ): NetworkResponse<WeatherNetworkModel, NetworkError>

}