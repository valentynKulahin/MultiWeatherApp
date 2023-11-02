package com.example.data.repo.news.remote

import com.example.common.ApiResult

interface NewsDataRemoteRepo {

    suspend fun getTopNews(): ApiResult

}