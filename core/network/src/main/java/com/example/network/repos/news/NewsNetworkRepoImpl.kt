package com.example.network.repos.news

import com.example.common.ApiResult
import com.example.network.api.NewsApi
import com.example.network.di.NewsRetrofit
import com.example.network.repos.execute
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsNetworkRepoImpl @Inject constructor(
    @NewsRetrofit private val newsApi: NewsApi
) : NewsNetworkRepo {

    override suspend fun getTopNews(country: String, apiKey: String): ApiResult =
        execute { newsApi.getTopHeadlineNews(country = country, apiKey = apiKey) }

}