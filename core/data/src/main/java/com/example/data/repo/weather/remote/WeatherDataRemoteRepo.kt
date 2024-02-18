package com.example.data.repo.weather.remote

import com.example.model.model.country.CountryItemExternalModel
import com.example.model.model.weather.WeatherExternalModel
import com.example.common.network.response.DataResponse
import com.example.common.network.response.DataResponseError

interface WeatherDataRemoteRepo {

    suspend fun getForecastWeather(
        country: String,
        days: Int,
        aqi: String,
        alerts: String
    ): DataResponse<WeatherExternalModel, DataResponseError>

    suspend fun getForecastWeatherByLatLon(
        latLon: String,
        days: Int,
        aqi: String,
        alerts: String
    ): DataResponse<WeatherExternalModel, DataResponseError>

    suspend fun getSearchingCountriesList(country: String): DataResponse<List<CountryItemExternalModel>, DataResponseError>

}