package com.example.network.repos.weather

import com.example.common.ApiResult
import com.example.datastore.repo.DataStoreRepo
import com.example.network.api.WeatherApi
import com.example.network.di.WeatherRetrofit
import com.example.network.repos.execute
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherNetworkRepoImpl @Inject constructor(
    @WeatherRetrofit private val weatherApi: WeatherApi,
    private val dataStoreRepo: DataStoreRepo
) : WeatherNetworkRepo {

    override suspend fun getForecastWeather(
        country: String,
        days: Int,
        aqi: String,
        alerts: String
    ): ApiResult {
        val apiKey = dataStoreRepo.getWeatherToken().first()
        return execute {
            weatherApi.getForecastWeather(
                apiKey = apiKey,
                country = country,
                days = days,
                aqi = aqi,
                alerts = alerts
            )
        }
    }
    
}
