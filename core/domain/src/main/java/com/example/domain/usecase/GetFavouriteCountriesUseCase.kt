package com.example.domain.usecase

import com.example.data.repo.weather.local.WeatherDataLocalRepo
import com.example.model.model.country.CountryItemExternalModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetFavouriteCountriesUseCase @Inject constructor(
    private val weatherDataLocalRepo: WeatherDataLocalRepo
) {

    operator fun invoke(): Flow<List<CountryItemExternalModel>> =
        weatherDataLocalRepo.getFavouriteCountries()

}