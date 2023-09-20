package com.example.network.di

import com.example.network.repos.CurrentWeatherApi
import com.example.network.repos.CurrentWeatherApiImpl
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
    fun CurrentWeatherApi(

    ): CurrentWeatherApi {
        return CurrentWeatherApiImpl()
    }

}