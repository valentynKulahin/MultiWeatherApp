package com.example.data.di

import com.example.data.repo.DataRepo
import com.example.data.repo.DataRepoImpl
import com.example.data.repo.news.local.NewsDataLocalRepo
import com.example.data.repo.news.local.NewsDataLocalRepoImpl
import com.example.data.repo.news.remote.NewsDataRemoteRepo
import com.example.data.repo.news.remote.NewsDataRemoteRepoImpl
import com.example.data.repo.weather.local.WeatherDataLocalRepo
import com.example.data.repo.weather.local.WeatherDataLocalRepoImpl
import com.example.data.repo.weather.remote.WeatherDataRemoteRepo
import com.example.data.repo.weather.remote.WeatherDataRemoteRepoImpl
import com.example.data.util.NetworkMonitor
import com.example.network.repos.news.NewsNetworkRepo
import com.example.network.repos.weather.WeatherNetworkRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataRepoModule {

    @Provides
    @Singleton
    fun provideDataRepo(
        networkMonitor: NetworkMonitor,
        newsDataLocalRepo: NewsDataLocalRepo,
        newsDataRemoteRepo: NewsDataRemoteRepo,
        weatherDataLocalRepo: WeatherDataLocalRepo,
        weatherDataRemoteRepo: WeatherDataRemoteRepo
    ): DataRepo {
        return DataRepoImpl(
            networkMonitor = networkMonitor,
            newsDataRemoteRepo = newsDataRemoteRepo,
            newsDataLocalRepo = newsDataLocalRepo,
            weatherDataLocalRepo = weatherDataLocalRepo,
            weatherDataRemoteRepo = weatherDataRemoteRepo
        )
    }

    @Provides
    @Singleton
    fun provideNewsDataLocalRepo(

    ) : NewsDataLocalRepo {
        return NewsDataLocalRepoImpl()
    }

    @Provides
    @Singleton
    fun provideNewsDataRemoteRepo(
        newsNetworkRepo: NewsNetworkRepo
    ) : NewsDataRemoteRepo {
        return NewsDataRemoteRepoImpl(newsNetworkRepo = newsNetworkRepo)
    }

    @Provides
    @Singleton
    fun provideWeatherDataLocalRepo(

    ) : WeatherDataLocalRepo {
        return WeatherDataLocalRepoImpl()
    }

    @Provides
    @Singleton
    fun provideWeatherDataRemoteRepo(
        weatherNetworkRepo: WeatherNetworkRepo
    ) : WeatherDataRemoteRepo {
        return WeatherDataRemoteRepoImpl(weatherNetworkRepo = weatherNetworkRepo)
    }

}