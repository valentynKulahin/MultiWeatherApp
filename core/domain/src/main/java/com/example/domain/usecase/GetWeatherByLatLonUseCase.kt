package com.example.domain.usecase

import com.example.data.repo.DataRepo
import com.example.domain.model.weather.WeatherDomainModel
import com.example.domain.util.mapToDomain
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetWeatherByLatLonUseCase @Inject constructor(
    private val dataRepo: DataRepo
) {

    suspend operator fun invoke(latLon: String): WeatherDomainModel {
        return dataRepo.getForecastWeatherByLatLon(latLon = latLon).mapToDomain()
    }

}