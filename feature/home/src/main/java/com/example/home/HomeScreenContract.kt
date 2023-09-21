package com.example.home

import com.example.domain.model.current.CurrentWeather
import com.example.domain.model.forecast.ForecastWeather

data class HomeScreenContract(
    val currentWeather: CurrentWeather = CurrentWeather(),
    val forecastWeather: ForecastWeather = ForecastWeather()
)

sealed class HomeScreenIntent {

    data object UpdateCurrentWeather : HomeScreenIntent()

    data object UpdateForecastWeather : HomeScreenIntent()

}
