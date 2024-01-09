package com.example.data.repo

import com.example.common.ApiResult
import com.example.common.model.search.SearchingResult
import com.example.data.model.news.NewsDataModel
import com.example.data.model.weather.WeatherDataModel
import com.example.data.repo.news.local.NewsDataLocalRepo
import com.example.data.repo.news.remote.NewsDataRemoteRepo
import com.example.data.repo.weather.local.WeatherDataLocalRepo
import com.example.data.repo.weather.remote.WeatherDataRemoteRepo
import com.example.data.util.NetworkMonitor
import com.example.data.util.NetworkStatus
import com.example.data.util.mappers.mapToData
import com.example.network.models.news.NewsNetworkModel
import com.example.network.models.weather.WeatherNetworkModel
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
                        val news = result.data as NewsNetworkModel
                        return news.mapToData()
                    }

                    is ApiResult.ApiException -> {
                        NewsDataModel()
                    }

                    is ApiResult.ApiError -> {
                        NewsDataModel()
                    }
                }
            }

            else -> {}
        }
        return NewsDataModel()
    }

    override suspend fun getForecastWeather(country: String): WeatherDataModel {
        when (networkMonitor.networkStatus.first()) {
            is NetworkStatus.Connected -> {
                return when (val result =
                    weatherDataRemoteRepo.getForecastWeather(country = country)) {
                    is ApiResult.ApiSuccess -> {
                        val weather = result.data as WeatherNetworkModel
                        return weather.mapToData()
                    }

                    is ApiResult.ApiException -> {
                        WeatherDataModel()
                    }

                    is ApiResult.ApiError -> {
                        WeatherDataModel()
                    }
                }
            }

            else -> {}
        }
        return WeatherDataModel()
    }

    override suspend fun getSearchingCountriesList(country: String): SearchingResult {
        when (networkMonitor.networkStatus.first()) {
            is NetworkStatus.Connected -> {
                return when (val result =
                    weatherDataRemoteRepo.getSearchingCountriesList(country = country)) {
                    is ApiResult.ApiSuccess -> {
                        val searchingList = result.data as SearchingResult
                        return searchingList
                    }

                    is ApiResult.ApiException -> {
                        SearchingResult()
                    }

                    is ApiResult.ApiError -> {
                        SearchingResult()
                    }
                }
            }

            else -> {}
        }
        return SearchingResult()
    }

}