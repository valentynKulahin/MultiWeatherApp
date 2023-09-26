package com.example.database.di

import com.example.database.WeatherDatabase
import com.example.database.dao.FavouritesCountriesDao
import com.example.database.dao.SearchCountriesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {

    @Provides
    fun providesFavouriteCountriesDao(
        database: WeatherDatabase
    ): FavouritesCountriesDao = database.favouritesCountriesDao()

    @Provides
    fun providesSearchCountriesDao(
        database: WeatherDatabase
    ): SearchCountriesDao = database.searchCountriesDao()

}
