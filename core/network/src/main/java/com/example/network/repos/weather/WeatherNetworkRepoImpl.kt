package com.example.network.repos.weather

import com.example.network.utils.NetworkError
import com.example.network.utils.NetworkResponse
import com.example.network.api.WeatherApi
import com.example.network.di.WeatherRetrofit
import com.example.network.models.weather.WeatherNetworkModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class WeatherNetworkRepoImpl @Inject constructor(
    @WeatherRetrofit private val weatherApi: WeatherApi
) : WeatherNetworkRepo {

    override suspend fun getForecastWeather(
        country: String,
        days: Int,
        aqi: String,
        alerts: String
    ): NetworkResponse<WeatherNetworkModel, NetworkError> {
        return weatherApi.getForecastWeather(
            country = country,
            days = days,
            aqi = aqi,
            alerts = alerts
        )
    }

    override suspend fun getForecastWeatherByLatLon(
        latLon: String,
        days: Int,
        aqi: String,
        alerts: String
    ): NetworkResponse<WeatherNetworkModel, NetworkError> {
        return weatherApi.getForecastWeatherByLatLon(
            latLon = latLon,
            days = days,
            aqi = aqi,
            alerts = alerts
        )
    }

}
