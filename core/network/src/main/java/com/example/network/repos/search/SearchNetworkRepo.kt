package com.example.network.repos.search

import com.example.network.utils.NetworkError
import com.example.network.utils.NetworkResponse
import com.example.network.models.country.CountryItemNetworkModel

interface SearchNetworkRepo {

    suspend fun searchCountry(country: String): NetworkResponse<List<CountryItemNetworkModel>, NetworkError>

}