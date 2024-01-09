package com.example.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.database.model.SearchingHistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchingHistoryCountriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearchCountry(searchingHistoryEntity: SearchingHistoryEntity)

    @Query(value = "SELECT * FROM search_history")
    fun getSearchCountries(): Flow<List<SearchingHistoryEntity>>

}