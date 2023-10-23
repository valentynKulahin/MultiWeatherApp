package com.example.network.repos.weather

import com.example.common.ApiResult
import com.example.common.model.KeyModel

interface WeatherRepo {

    suspend fun getCurrentWeather(key: KeyModel) : ApiResult

    suspend fun getForecastWeather(key: KeyModel) : ApiResult

}