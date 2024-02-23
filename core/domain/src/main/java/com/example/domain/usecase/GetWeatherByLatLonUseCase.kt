package com.example.domain.usecase

import com.example.data.repo.weather.remote.WeatherDataRemoteRepo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetWeatherByLatLonUseCase @Inject constructor(
    private val weatherDataRemoteRepo: WeatherDataRemoteRepo
) {

    suspend operator fun invoke(latLon: String) =
        weatherDataRemoteRepo.getForecastWeatherByLatLon(
            latLon = latLon,
            days = 7,
            aqi = "yes",
            alerts = "yes"
        )

}