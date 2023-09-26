package com.example.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.database.dao.FavouritesCountriesDao
import com.example.database.dao.SearchCountriesDao
import com.example.database.model.CountriesEntity
import com.example.database.model.SearchHistoryEntity

@Database(
    entities =
    [
        CountriesEntity::class,
        SearchHistoryEntity::class
    ],
    version = 1,
    exportSchema = true
)

abstract class WeatherDatabase : RoomDatabase() {

    abstract fun favouritesCountriesDao(): FavouritesCountriesDao

    abstract fun searchCountriesDao(): SearchCountriesDao

}