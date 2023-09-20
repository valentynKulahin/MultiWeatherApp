package com.example.network.repos

import com.example.common.ApiResult
import com.example.common.model.KeyModel

interface CurrentWeatherApi {

    suspend fun getCurrentWeather(key: KeyModel) : ApiResult

}