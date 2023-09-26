package com.example.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.database.model.CountriesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouritesCountriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavouriteCountry(countriesEntity: CountriesEntity)

    @Query(value = "SELECT * FROM favourites_countries")
    fun getFavouritesCountries(): Flow<List<CountriesEntity>>

}