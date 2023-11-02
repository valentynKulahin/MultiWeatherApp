package com.example.data.repo

import com.example.data.model.news.NewsDataModel
import com.example.data.model.weather.WeatherDataModel

interface DataRepo {

    suspend fun getTopNews() : NewsDataModel

    suspend fun getForecastWeather() : WeatherDataModel

}