package com.example.network.repos.search

import com.example.network.utils.NetworkError
import com.example.network.utils.NetworkResponse
import com.example.network.api.WeatherApi
import com.example.network.di.WeatherRetrofit
import com.example.network.models.country.CountryItemNetworkModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class SearchNetworkRepoImpl @Inject constructor(
    @WeatherRetrofit private val weatherApi: WeatherApi
) : SearchNetworkRepo {

    override suspend fun searchCountry(country: String): NetworkResponse<List<CountryItemNetworkModel>, NetworkError> {
        return weatherApi.getSearchingCountriesList(country = country)
    }

}