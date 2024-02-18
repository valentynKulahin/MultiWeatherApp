package com.example.model.model.country

import com.example.model.model.news.NewsExternalModel
import com.example.model.model.weather.WeatherExternalModel

data class CountryItemExternalModel(
    val id: Long? = null,
    val country: String? = null,
    val lat: Double? = null,
    val lon: Double? = null,
    val name: String? = null,
    val region: String? = null,
    val url: String? = null,
    val weather: WeatherExternalModel = WeatherExternalModel(),
    val news: NewsExternalModel = NewsExternalModel()
)