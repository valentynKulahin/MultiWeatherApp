package com.example.home

import com.example.domain.model.news.NewsDomainModel
import com.example.domain.model.weather.WeatherDomainModel

data class HomeScreenContract(
    val news: NewsDomainModel = NewsDomainModel(),
    val weather: WeatherDomainModel = WeatherDomainModel()
)

sealed class HomeScreenIntent {

    data object UpdateWeather : HomeScreenIntent()

    data object UpdateNews : HomeScreenIntent()

}
