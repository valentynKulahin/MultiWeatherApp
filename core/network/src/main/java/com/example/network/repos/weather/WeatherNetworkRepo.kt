package com.example.network.repos.weather

import com.example.common.ApiResult

interface WeatherNetworkRepo {

    suspend fun getForecastWeather(apiKey: String, country: String, days: Int, aqi: String, alerts: String) : ApiResult

}