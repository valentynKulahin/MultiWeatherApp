package com.example.data.repo.weather.local

import com.example.data.model.country.CountryItemDataModel
import kotlinx.coroutines.flow.Flow

interface WeatherDataLocalRepo {

    suspend fun addCountryToFavourite(countryItemDataModel: CountryItemDataModel)

    fun getFavouriteCountries(): Flow<List<CountryItemDataModel>>

    suspend fun deleteCountryFromFavourite(countryItemDataModel: CountryItemDataModel)

}