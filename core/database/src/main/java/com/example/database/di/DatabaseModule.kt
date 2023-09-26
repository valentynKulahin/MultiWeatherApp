package com.example.database.di

import android.content.Context
import androidx.room.Room
import com.example.database.WeatherDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesWeatherDatabase(
        @ApplicationContext context: Context
    ): WeatherDatabase = Room.databaseBuilder(
        context = context,
        klass = WeatherDatabase::class.java,
        name = "weather-database"
    ).build()

}