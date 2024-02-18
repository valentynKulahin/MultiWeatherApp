package com.example.data.repo.weather.remote

import com.example.model.model.country.CountryItemExternalModel
import com.example.model.model.weather.WeatherExternalModel
import com.example.data.util.mappers.mapToData
import com.example.common.network.response.DataResponse
import com.example.common.network.response.DataResponseError
import com.example.network.repos.search.SearchNetworkRepo
import com.example.network.repos.weather.WeatherNetworkRepo
import com.example.network.utils.NetworkResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class WeatherDataRemoteRepoImpl @Inject constructor(
    private val weatherNetworkRepo: WeatherNetworkRepo,
    private val searchNetworkRepo: SearchNetworkRepo
) : WeatherDataRemoteRepo {

    override suspend fun getForecastWeather(
        country: String,
        days: Int,
        aqi: String,
        alerts: String
    ): DataResponse<WeatherExternalModel, DataResponseError> {
        val result = weatherNetworkRepo.getForecastWeather(
            country = country,
            days = days,
            aqi = aqi,
            alerts = alerts
        )
        return when (result) {
            is NetworkResponse.ApiSuccess -> {
                DataResponse.Success(result.body.mapToData())
            }

            is NetworkResponse.ApiError -> {
                DataResponse.Error(DataResponseError(result.code, result.message.message))
            }

            is NetworkResponse.UnknownError -> {
                DataResponse.Error(DataResponseError(0, "Unknown error"))
            }
        }
    }


    override suspend fun getForecastWeatherByLatLon(
        latLon: String,
        days: Int,
        aqi: String,
        alerts: String
    ): DataResponse<WeatherExternalModel, DataResponseError> {
        val result = weatherNetworkRepo.getForecastWeatherByLatLon(
            latLon = latLon,
            days = days,
            aqi = aqi,
            alerts = alerts
        )
        return when (result) {
            is NetworkResponse.ApiSuccess -> {
                DataResponse.Success(result.body.mapToData())
            }

            is NetworkResponse.ApiError -> {
                DataResponse.Error(DataResponseError(result.code, result.message.message))
            }

            is NetworkResponse.UnknownError -> {
                DataResponse.Error(DataResponseError(0, "Unknown error"))
            }
        }
    }

    override suspend fun getSearchingCountriesList(country: String): DataResponse<List<CountryItemExternalModel>, DataResponseError> {
        val result = searchNetworkRepo.searchCountry(country = country)
        return when (result) {
            is NetworkResponse.ApiSuccess -> {
                DataResponse.Success(result.body.mapToData())
            }

            is NetworkResponse.ApiError -> {
                DataResponse.Error(DataResponseError(result.code, result.message.message))
            }

            is NetworkResponse.UnknownError -> {
                DataResponse.Error(DataResponseError(0, "Unknown error"))
            }
        }
    }

}