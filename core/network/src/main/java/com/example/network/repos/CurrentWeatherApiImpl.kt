package com.example.network.repos

import com.example.common.ApiResult
import com.example.common.model.KeyModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrentWeatherApiImpl @Inject constructor(

) : CurrentWeatherApi {

    override suspend fun getCurrentWeather(key: KeyModel): ApiResult {
        TODO("Not yet implemented")
    }

}

