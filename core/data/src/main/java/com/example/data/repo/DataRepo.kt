package com.example.data.repo

import com.example.common.model.search.SearchingResult
import com.example.data.model.news.NewsDataModel
import com.example.data.model.weather.WeatherDataModel

interface DataRepo {

    suspend fun getTopNews() : NewsDataModel

    suspend fun getForecastWeather(country: String) : WeatherDataModel

    suspend fun getSearchingCountriesList(country: String) : SearchingResult

}