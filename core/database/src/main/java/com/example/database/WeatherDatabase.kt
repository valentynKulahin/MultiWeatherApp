package com.example.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.database.dao.FavouritesCountriesDao
import com.example.database.dao.SearchingHistoryCountriesDao
import com.example.database.model.CountryItemDatabaseModel
import com.example.database.model.SearchingHistoryEntity

@Database(
    entities =
    [
        CountryItemDatabaseModel::class,
        SearchingHistoryEntity::class
    ],
    version = 1,
    exportSchema = false
)
//
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun favouritesCountriesDao(): FavouritesCountriesDao

    abstract fun searchCountriesDao(): SearchingHistoryCountriesDao

}