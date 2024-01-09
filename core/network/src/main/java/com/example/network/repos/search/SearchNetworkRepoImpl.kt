package com.example.network.repos.search

import com.example.common.ApiResult
import com.example.datastore.repo.DataStoreRepo
import com.example.network.api.WeatherApi
import com.example.network.di.WeatherRetrofit
import com.example.network.repos.execute
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchNetworkRepoImpl @Inject constructor(
    @WeatherRetrofit private val weatherApi: WeatherApi,
    private val dataStoreRepo: DataStoreRepo
): SearchNetworkRepo {

    override suspend fun searchCountry(country: String): ApiResult {
        val apiKey = dataStoreRepo.getWeatherToken().first()
        return execute {
            weatherApi.getSearchingCountriesList(apiKey = apiKey, country = country)
        }
    }

}