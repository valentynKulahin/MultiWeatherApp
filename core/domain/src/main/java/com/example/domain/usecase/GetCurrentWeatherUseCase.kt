package com.example.domain.usecase

import com.example.domain.model.weather.CurrentDomainModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCurrentWeatherUseCase @Inject constructor(

) {

    operator fun invoke(): CurrentDomainModel {
        return CurrentDomainModel()
    }

}