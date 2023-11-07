package com.example.network.repos.news

import com.example.common.ApiResult
import com.example.datastore.repo.DataStoreRepo
import com.example.network.api.NewsApi
import com.example.network.di.NewsRetrofit
import com.example.network.repos.execute
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsNetworkRepoImpl @Inject constructor(
    @NewsRetrofit private val newsApi: NewsApi,
    private val dataStoreRepo: DataStoreRepo
) : NewsNetworkRepo {

    override suspend fun getTopNews(country: String): ApiResult {
        val apiKey = dataStoreRepo.getNewsToken().first()
        return execute {
            newsApi.getTopHeadlineNews(
                country = country,
                apiKey = apiKey
            )
        }
    }

}