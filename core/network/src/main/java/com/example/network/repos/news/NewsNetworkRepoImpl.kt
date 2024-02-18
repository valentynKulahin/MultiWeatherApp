package com.example.network.repos.news

import com.example.network.utils.NetworkError
import com.example.network.utils.NetworkResponse
import com.example.network.api.NewsApi
import com.example.network.di.NewsRetrofit
import com.example.network.models.news.NewsNetworkModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class NewsNetworkRepoImpl @Inject constructor(
    @NewsRetrofit private val newsApi: NewsApi
) : NewsNetworkRepo {

    override suspend fun getTopNews(country: String): NetworkResponse<NewsNetworkModel, NetworkError> {
        return newsApi.getTopHeadlineNews(country = country)
    }

}