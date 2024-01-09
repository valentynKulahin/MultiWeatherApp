package com.example.data.repo.weather.remote

import com.example.common.ApiResult

interface WeatherDataRemoteRepo {

    suspend fun getForecastWeather(country: String): ApiResult

    suspend fun getSearchingCountriesList(country: String): ApiResult

}