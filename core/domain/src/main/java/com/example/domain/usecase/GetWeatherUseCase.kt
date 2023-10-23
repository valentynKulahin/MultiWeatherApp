package com.example.domain.usecase

import com.example.domain.model.weather.WeatherDomainModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetWeatherUseCase @Inject constructor(

) {

    operator fun invoke(): WeatherDomainModel {
        return WeatherDomainModel()
    }

}