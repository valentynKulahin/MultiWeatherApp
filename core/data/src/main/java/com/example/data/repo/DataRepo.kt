package com.example.data.repo

import com.example.data.model.country.CountryItemDataModel
import com.example.data.model.news.NewsDataModel
import com.example.data.model.weather.WeatherDataModel
import kotlinx.coroutines.flow.Flow

interface DataRepo {

    suspend fun getTopNews(country: String) : NewsDataModel

    suspend fun getForecastWeather(country: String) : WeatherDataModel

    suspend fun getForecastWeatherByLatLon(latLon: String) : WeatherDataModel

    suspend fun getSearchingCountriesList(country: String) : List<CountryItemDataModel>

    fun getFavouriteCountries(): Flow<List<CountryItemDataModel>>

    suspend fun addCountryToFavourite(countryItemDataModel: CountryItemDataModel)

    suspend fun deleteCountryFromFavourite(countryItemDataModel: CountryItemDataModel)

}