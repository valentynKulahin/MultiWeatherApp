package com.example.network.di

import com.example.network.repos.news.NewsRepo
import com.example.network.repos.news.NewsRepoImpl
import com.example.network.repos.weather.WeatherRepo
import com.example.network.repos.weather.WeatherRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule() {

    @Provides
    @Singleton
    fun provideWeatherRepo(

    ): WeatherRepo {
        return WeatherRepoImpl()
    }

    @Provides
    @Singleton
    fun provideNewsRepo(

    ): NewsRepo {
        return NewsRepoImpl()
    }

}