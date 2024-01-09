package com.example.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.database.dao.FavouritesCountriesDao
import com.example.database.dao.SearchingHistoryCountriesDao
import com.example.database.model.CountriesEntity
import com.example.database.model.SearchingHistoryEntity

@Database(
    entities =
    [
        CountriesEntity::class,
        SearchingHistoryEntity::class
    ],
    version = 1,
    exportSchema = true
)

abstract class WeatherDatabase : RoomDatabase() {

    abstract fun favouritesCountriesDao(): FavouritesCountriesDao

    abstract fun searchCountriesDao(): SearchingHistoryCountriesDao

}