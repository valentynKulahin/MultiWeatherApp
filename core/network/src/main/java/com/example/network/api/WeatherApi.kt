package com.example.network.api

import com.example.network.utils.NetworkError
import com.example.network.utils.NetworkResponse
import com.example.network.models.country.CountryItemNetworkModel
import com.example.network.models.weather.WeatherNetworkModel
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface WeatherApi {

    @Headers("Content-Type: application/json")
    @GET(value = "forecast.json")
    suspend fun getForecastWeather(
        @Query("q") country: String,
        @Query("days") days: Int,
        @Query("aqi") aqi: String,
        @Query("alerts") alerts: String
    ): NetworkResponse<WeatherNetworkModel, NetworkError>

    @Headers("Content-Type: application/json")
    @GET(value = "forecast.json")
    suspend fun getForecastWeatherByLatLon(
        @Query("q") latLon: String,
        @Query("days") days: Int,
        @Query("aqi") aqi: String,
        @Query("alerts") alerts: String
    ): NetworkResponse<WeatherNetworkModel, NetworkError>

    @Headers("Content-Type: application/json")
    @GET(value = "search.json")
    suspend fun getSearchingCountriesList(
        @Query("q") country: String
    ): NetworkResponse<List<CountryItemNetworkModel>, NetworkError>

}