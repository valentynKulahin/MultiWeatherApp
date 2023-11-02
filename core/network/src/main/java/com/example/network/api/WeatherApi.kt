package com.example.network.api

import com.example.network.models.weather.WeatherNetworkModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface WeatherApi {

    @Headers("Content-Type: application/json")
    @GET(value = "/current.json")
    fun getForecastWeather(
        @Query("key") apiKey: String,
        @Query("country") country: String,
        @Query("days") days: Int,
        @Query("aqi") aqi: String,
        @Query("alerts") alerts: String
    ): Call<WeatherNetworkModel>

}