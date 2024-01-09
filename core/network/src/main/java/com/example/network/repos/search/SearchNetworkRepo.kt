package com.example.network.repos.search

import com.example.common.ApiResult

interface SearchNetworkRepo {

    suspend fun searchCountry(country: String): ApiResult

}