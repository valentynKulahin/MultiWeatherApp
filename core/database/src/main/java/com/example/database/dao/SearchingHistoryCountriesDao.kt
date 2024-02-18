package com.example.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.database.model.SearchingHistoryEntity
import com.example.database.util.DatabaseResponse
import com.example.database.util.DatabaseResponseError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Dao
interface SearchingHistoryCountriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearchCountry(searchingHistoryEntity: SearchingHistoryEntity)

    @Query(value = "SELECT * FROM search_history")
    fun getSearchCountries(): Flow<DatabaseResponse<List<SearchingHistoryEntity>, DatabaseResponseError>> {
        return flow {
            try {
                DatabaseResponse.Success(getSearchCountries())
            } catch (e: Exception) {
                DatabaseResponse.Error(DatabaseResponseError(0, e.message.toString()))
            }
        }
    }

}