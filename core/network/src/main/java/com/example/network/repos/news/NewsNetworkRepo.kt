package com.example.network.repos.news

import com.example.common.ApiResult

interface NewsNetworkRepo {

    suspend fun getTopNews(country: String): ApiResult

}