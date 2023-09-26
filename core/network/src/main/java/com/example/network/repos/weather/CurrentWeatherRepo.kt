package com.example.network.repos.weather

import com.example.common.ApiResult
import com.example.common.model.KeyModel

interface CurrentWeatherRepo {

    suspend fun getCurrentWeather(key: KeyModel) : ApiResult

}