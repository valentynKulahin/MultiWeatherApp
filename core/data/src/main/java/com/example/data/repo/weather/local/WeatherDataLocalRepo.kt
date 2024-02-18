package com.example.data.repo.weather.local

import com.example.model.model.country.CountryItemExternalModel
import kotlinx.coroutines.flow.Flow

interface WeatherDataLocalRepo {

    suspend fun addCountryToFavourite(countryItemExternalModel: CountryItemExternalModel)

    fun getFavouriteCountries(): Flow<List<CountryItemExternalModel>>

    suspend fun deleteCountryFromFavourite(countryItemExternalModel: CountryItemExternalModel)

}