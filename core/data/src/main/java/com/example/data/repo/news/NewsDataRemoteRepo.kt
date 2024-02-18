package com.example.data.repo.news

import com.example.model.model.news.NewsExternalModel
import com.example.common.network.response.DataResponse
import com.example.common.network.response.DataResponseError

interface NewsDataRemoteRepo {

    suspend fun getTopNews(country: String): DataResponse<NewsExternalModel, DataResponseError>

}