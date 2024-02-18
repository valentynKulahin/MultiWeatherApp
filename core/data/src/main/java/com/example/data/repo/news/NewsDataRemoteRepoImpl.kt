package com.example.data.repo.news

import com.example.model.model.news.NewsExternalModel
import com.example.common.network.response.DataResponse
import com.example.network.utils.NetworkResponse
import com.example.data.util.mappers.mapToData
import com.example.common.network.response.DataResponseError
import com.example.network.repos.news.NewsNetworkRepo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class NewsDataRemoteRepoImpl @Inject constructor(
    private val newsNetworkRepo: NewsNetworkRepo
) : NewsDataRemoteRepo {

    override suspend fun getTopNews(country: String): DataResponse<NewsExternalModel, DataResponseError> {
        val result = newsNetworkRepo.getTopNews(country = country)
        return when (result) {
            is NetworkResponse.ApiSuccess -> {
                DataResponse.Success(result.body.mapToData())
            }

            is NetworkResponse.ApiError -> {
                DataResponse.Error(DataResponseError(result.code, result.message.message))
            }

            is NetworkResponse.UnknownError -> {
                DataResponse.Error(DataResponseError(0, "Unknown error"))
            }
        }
    }

}