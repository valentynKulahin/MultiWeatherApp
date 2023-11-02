package com.example.data.repo.news.remote

import com.example.common.ApiResult
import com.example.network.repos.news.NewsNetworkRepo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsDataRemoteRepoImpl @Inject constructor(
    private val newsNetworkRepo: NewsNetworkRepo
) : NewsDataRemoteRepo {

    override suspend fun getTopNews(): ApiResult {
        return newsNetworkRepo.getTopNews("de", "")
    }

}