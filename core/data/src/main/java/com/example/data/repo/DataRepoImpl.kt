package com.example.data.repo

import com.example.common.ApiResult
import com.example.data.model.news.NewsDataModel
import com.example.data.model.weather.WeatherDataModel
import com.example.data.repo.news.local.NewsDataLocalRepo
import com.example.data.repo.news.remote.NewsDataRemoteRepo
import com.example.data.repo.weather.local.WeatherDataLocalRepo
import com.example.data.repo.weather.remote.WeatherDataRemoteRepo
import com.example.data.util.NetworkMonitor
import com.example.data.util.NetworkStatus
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepoImpl @Inject constructor(
    private val networkMonitor: NetworkMonitor,
    private val newsDataRemoteRepo: NewsDataRemoteRepo,
    private val newsDataLocalRepo: NewsDataLocalRepo,
    private val weatherDataRemoteRepo: WeatherDataRemoteRepo,
    private val weatherDataLocalRepo: WeatherDataLocalRepo
) : DataRepo {

    override suspend fun getTopNews(): NewsDataModel {
        when (networkMonitor.networkStatus.first()) {
            is NetworkStatus.Connected -> {
                return when (val result = newsDataRemoteRepo.getTopNews()) {
                    is ApiResult.ApiSuccess -> {
                        result.data as NewsDataModel
                    }
                    is ApiResult.ApiException -> { NewsDataModel() }
                    is ApiResult.ApiError -> { NewsDataModel() }
                }
            }
            else -> {}
        }
        return NewsDataModel()
    }

    override suspend fun getForecastWeather(): WeatherDataModel {
        when (networkMonitor.networkStatus.first()) {
            is NetworkStatus.Connected -> {
                return when (val result = weatherDataRemoteRepo.getForecastWeather()) {
                    is ApiResult.ApiSuccess -> {
                        result.data as WeatherDataModel
                    }
                    is ApiResult.ApiException -> { WeatherDataModel() }
                    is ApiResult.ApiError -> { WeatherDataModel() }
                }
            }
            else -> {}
        }
        return WeatherDataModel()
    }

}