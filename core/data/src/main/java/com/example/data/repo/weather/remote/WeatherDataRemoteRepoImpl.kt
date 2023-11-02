package com.example.data.repo.weather.remote

import com.example.common.ApiResult
import com.example.network.repos.weather.WeatherNetworkRepo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherDataRemoteRepoImpl @Inject constructor(
    private val weatherNetworkRepo: WeatherNetworkRepo
) : WeatherDataRemoteRepo {

    override suspend fun getForecastWeather(): ApiResult {
        return weatherNetworkRepo.getForecastWeather(
            apiKey = "",
            country = "Essen",
            days = 2,
            aqi = "yes",
            alerts = "yes"
        )
    }

}