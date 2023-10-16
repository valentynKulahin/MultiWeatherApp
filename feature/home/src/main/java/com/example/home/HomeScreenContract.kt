package com.example.home

import com.example.domain.model.current.CurrentWeather
import com.example.domain.model.forecast.ForecastWeather
import com.example.domain.model.news.NewsResult

data class HomeScreenContract(
    val currentWeather: CurrentWeather = CurrentWeather(),
    val news: NewsResult = NewsResult(),
    val forecastWeather: ForecastWeather = ForecastWeather()
)

sealed class HomeScreenIntent {

    data object UpdateCurrentWeather : HomeScreenIntent()

    data object UpdateForecastWeather : HomeScreenIntent()

    data object UpdateNews : HomeScreenIntent()

}
