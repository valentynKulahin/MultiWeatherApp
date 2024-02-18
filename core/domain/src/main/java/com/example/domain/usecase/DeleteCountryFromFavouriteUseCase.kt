package com.example.domain.usecase

import com.example.data.repo.weather.local.WeatherDataLocalRepo
import com.example.model.model.country.CountryItemExternalModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeleteCountryFromFavouriteUseCase @Inject constructor(
    private val weatherDataLocalRepo: WeatherDataLocalRepo
) {

    suspend operator fun invoke(countryItemExternalModel: CountryItemExternalModel) =
        weatherDataLocalRepo.deleteCountryFromFavourite(countryItemExternalModel = countryItemExternalModel)

}