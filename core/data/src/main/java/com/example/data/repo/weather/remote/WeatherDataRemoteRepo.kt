package com.example.data.repo.weather.remote

import com.example.common.ApiResult

interface WeatherDataRemoteRepo {

    suspend fun getForecastWeather(): ApiResult

}