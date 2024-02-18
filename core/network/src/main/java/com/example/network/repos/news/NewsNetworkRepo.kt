package com.example.network.repos.news

import com.example.network.utils.NetworkError
import com.example.network.utils.NetworkResponse
import com.example.network.models.news.NewsNetworkModel

interface NewsNetworkRepo {

    suspend fun getTopNews(country: String): NetworkResponse<NewsNetworkModel, NetworkError>

}