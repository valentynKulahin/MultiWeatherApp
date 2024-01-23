package com.example.network.api

import com.example.common.model.search.SearchResultItem
import com.example.network.models.weather.WeatherNetworkModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface WeatherApi {

    @Headers("Content-Type: application/json")
    @GET(value = "forecast.json")
    fun getForecastWeather(
        @Query("key") apiKey: String,
        @Query("q") country: String,
        @Query("days") days: Int,
        @Query("aqi") aqi: String,
        @Query("alerts") alerts: String
    ): Call<WeatherNetworkModel>

    @Headers("Content-Type: application/json")
    @GET(value = "forecast.json")
    fun getForecastWeatherByLatLon(
        @Query("key") apiKey: String,
        @Query("q") latLon: String,
        @Query("days") days: Int,
        @Query("aqi") aqi: String,
        @Query("alerts") alerts: String
    ): Call<WeatherNetworkModel>

    @Headers("Content-Type: application/json")
    @GET(value = "search.json")
    fun getSearchingCountriesList(
        @Query("key") apiKey: String,
        @Query("q") country: String
    ): Call<List<SearchResultItem>>

}