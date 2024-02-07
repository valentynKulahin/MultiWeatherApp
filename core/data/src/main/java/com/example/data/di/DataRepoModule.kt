package com.example.data.di

import com.example.data.repo.DataRepo
import com.example.data.repo.DataRepoImpl
import com.example.data.repo.news.NewsDataRemoteRepo
import com.example.data.repo.news.NewsDataRemoteRepoImpl
import com.example.data.repo.weather.local.WeatherDataLocalRepo
import com.example.data.repo.weather.local.WeatherDataLocalRepoImpl
import com.example.data.repo.weather.remote.WeatherDataRemoteRepo
import com.example.data.repo.weather.remote.WeatherDataRemoteRepoImpl
import com.example.data.util.NetworkMonitor
import com.example.database.dao.FavouritesCountriesDao
import com.example.network.repos.news.NewsNetworkRepo
import com.example.network.repos.search.SearchNetworkRepo
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
        newsDataRemoteRepo: NewsDataRemoteRepo,
        weatherDataLocalRepo: WeatherDataLocalRepo,
        weatherDataRemoteRepo: WeatherDataRemoteRepo
    ): DataRepo {
        return DataRepoImpl(
            networkMonitor = networkMonitor,
            newsDataRemoteRepo = newsDataRemoteRepo,
            weatherDataLocalRepo = weatherDataLocalRepo,
            weatherDataRemoteRepo = weatherDataRemoteRepo
        )
    }

    @Provides
    @Singleton
    fun provideNewsDataRemoteRepo(
        newsNetworkRepo: NewsNetworkRepo
    ): NewsDataRemoteRepo {
        return NewsDataRemoteRepoImpl(newsNetworkRepo = newsNetworkRepo)
    }

    @Provides
    @Singleton
    fun provideWeatherDataLocalRepo(
        favouriteCountriesRepo: FavouritesCountriesDao
    ): WeatherDataLocalRepo {
        return WeatherDataLocalRepoImpl(favouriteCountriesRepo = favouriteCountriesRepo)
    }

    @Provides
    @Singleton
    fun provideWeatherDataRemoteRepo(
        weatherNetworkRepo: WeatherNetworkRepo,
        searchNetworkRepo: SearchNetworkRepo
    ): WeatherDataRemoteRepo {
        return WeatherDataRemoteRepoImpl(
            weatherNetworkRepo = weatherNetworkRepo,
            searchNetworkRepo = searchNetworkRepo
        )
    }

}