package com.example.network.repos.weather

import com.example.common.ApiResult
import com.example.common.model.KeyModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrentWeatherRepoImpl @Inject constructor(

) : CurrentWeatherRepo {

    override suspend fun getCurrentWeather(key: KeyModel): ApiResult {
        TODO("Not yet implemented")
    }

}

