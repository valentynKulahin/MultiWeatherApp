package com.example.network.api

import com.example.common.ApiResult
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RealTimeApi {

    @Headers("Content-Type: application/json")
    @GET(value = "/current.json")
    fun getCurrentWeather(@Query("key") key: String): ApiResult

}