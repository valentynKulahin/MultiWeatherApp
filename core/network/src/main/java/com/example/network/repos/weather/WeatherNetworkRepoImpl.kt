package com.example.network.repos.weather

import com.example.common.ApiResult
import com.example.network.api.WeatherApi
import com.example.network.di.WeatherRetrofit
import com.example.network.repos.execute
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherNetworkRepoImpl @Inject constructor(
    @WeatherRetrofit private val weatherApi: WeatherApi
) : WeatherNetworkRepo {

    override suspend fun getForecastWeather(
        apiKey: String,
        country: String,
        days: Int,
        aqi: String,
        alerts: String
    ): ApiResult =
        execute {
            weatherApi.getForecastWeather(
                apiKey = apiKey,
                country = country,
                days = days,
                aqi = aqi,
                alerts = alerts
            )
        }


}

