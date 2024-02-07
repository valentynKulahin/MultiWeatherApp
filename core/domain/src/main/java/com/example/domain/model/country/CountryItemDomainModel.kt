package com.example.domain.model.country

import com.example.domain.model.news.NewsDomainModel
import com.example.domain.model.weather.WeatherDomainModel

data class CountryItemDomainModel(
    val id: Long? = null,
    val country: String? = null,
    val lat: Double? = null,
    val lon: Double? = null,
    val name: String? = null,
    val region: String? = null,
    val url: String? = null,
    val weather: WeatherDomainModel = WeatherDomainModel(),
    val news: NewsDomainModel = NewsDomainModel()
)