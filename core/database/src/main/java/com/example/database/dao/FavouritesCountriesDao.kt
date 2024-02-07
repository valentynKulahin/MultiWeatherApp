package com.example.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.database.model.CountryItemDatabaseModel
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouritesCountriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavouriteCountry(countryItemDatabaseModel: CountryItemDatabaseModel)

    @Query(value = "SELECT * FROM favourites_countries")
    fun getFavouritesCountries(): Flow<List<CountryItemDatabaseModel>>

    @Query(value = "DELETE FROM favourites_countries WHERE id = :id")
    suspend fun deleteCountryFromFavourite(id: Long)

}