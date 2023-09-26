package com.example.network.di

import com.example.network.repos.news.NewsRepo
import com.example.network.repos.news.NewsRepoImpl
import com.example.network.repos.weather.CurrentWeatherRepo
import com.example.network.repos.weather.CurrentWeatherRepoImpl
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
    fun CurrentWeatherRepo(

    ): CurrentWeatherRepo {
        return CurrentWeatherRepoImpl()
    }

    @Provides
    @Singleton
    fun NewsRepo(

    ): NewsRepo {
        return NewsRepoImpl()
    }

}