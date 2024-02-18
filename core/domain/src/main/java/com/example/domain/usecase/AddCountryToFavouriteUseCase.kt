package com.example.domain.usecase

import com.example.data.repo.weather.local.WeatherDataLocalRepo
import com.example.model.model.country.CountryItemExternalModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AddCountryToFavouriteUseCase @Inject constructor(
    private val weatherDataLocalRepo: WeatherDataLocalRepo
) {

    suspend operator fun invoke(countryItemExternalModel: CountryItemExternalModel) {
        weatherDataLocalRepo.addCountryToFavourite(countryItemExternalModel = countryItemExternalModel)
    }

}