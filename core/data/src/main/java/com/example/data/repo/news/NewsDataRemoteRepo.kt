package com.example.data.repo.news

import com.example.common.ApiResult

interface NewsDataRemoteRepo {

    suspend fun getTopNews(country: String): ApiResult

}