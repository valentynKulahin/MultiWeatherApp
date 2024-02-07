package com.example.data.repo.news

import com.example.common.ApiResult
import com.example.network.repos.news.NewsNetworkRepo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class NewsDataRemoteRepoImpl @Inject constructor(
    private val newsNetworkRepo: NewsNetworkRepo
) : NewsDataRemoteRepo {

    override suspend fun getTopNews(country: String): ApiResult {
        return newsNetworkRepo.getTopNews(country = country)
    }

}