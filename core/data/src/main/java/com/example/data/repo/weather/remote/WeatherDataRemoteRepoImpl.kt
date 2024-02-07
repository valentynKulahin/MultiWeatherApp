package com.example.data.repo.weather.remote

import com.example.common.ApiResult
import com.example.network.repos.search.SearchNetworkRepo
import com.example.network.repos.weather.WeatherNetworkRepo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class WeatherDataRemoteRepoImpl @Inject constructor(
    private val weatherNetworkRepo: WeatherNetworkRepo,
    private val searchNetworkRepo: SearchNetworkRepo
) : WeatherDataRemoteRepo {

    override suspend fun getForecastWeather(country: String): ApiResult {
        return weatherNetworkRepo.getForecastWeather(
            country = country,
            days = 7,
            aqi = "yes",
            alerts = "yes"
        )
    }

    override suspend fun getForecastWeatherByLatLon(latLon: String): ApiResult {
        return weatherNetworkRepo.getForecastWeatherByLatLon(
            latLon = latLon,
            days = 1,
            aqi = "yes",
            alerts = "yes"
        )
    }

    override suspend fun getSearchingCountriesList(country: String): ApiResult {
        return searchNetworkRepo.searchCountry(country = country)
    }

}