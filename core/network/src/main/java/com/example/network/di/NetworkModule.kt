package com.example.network.di

import com.example.network.api.NewsApi
import com.example.network.api.WeatherApi
import com.example.network.repos.news.NewsNetworkRepo
import com.example.network.repos.news.NewsNetworkRepoImpl
import com.example.network.repos.weather.WeatherNetworkRepo
import com.example.network.repos.weather.WeatherNetworkRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideWeatherNetworkRepo(
        weatherApi: WeatherApi
    ): WeatherNetworkRepo {
        return WeatherNetworkRepoImpl(weatherApi = weatherApi)
    }

    @Singleton
    @Provides
    fun provideNewsNetworkRepo(
        newsApi: NewsApi
    ): NewsNetworkRepo {
        return NewsNetworkRepoImpl(newsApi = newsApi)
    }

}

@Qualifier
@Retention(value = AnnotationRetention.BINARY)
annotation class NewsRetrofit

@Qualifier
@Retention(value = AnnotationRetention.BINARY)
annotation class WeatherRetrofit