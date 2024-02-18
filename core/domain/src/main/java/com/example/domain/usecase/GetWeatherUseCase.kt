package com.example.domain.usecase

import com.example.data.repo.weather.remote.WeatherDataRemoteRepo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetWeatherUseCase @Inject constructor(
    private val weatherDataRemoteRepo: WeatherDataRemoteRepo
) {

    suspend operator fun invoke(country: String) =
        weatherDataRemoteRepo.getForecastWeather(
            country = country,
            days = 7,
            aqi = "yes",
            alerts = "yes"
        )

}