package com.example.datastore.repo

import kotlinx.coroutines.flow.Flow

interface DataStoreRepo {

    suspend fun updateNewsToken(token: String)

    suspend fun getNewsToken(): Flow<String>

    suspend fun updateWeatherToken(token: String)

    suspend fun getWeatherToken(): Flow<String>

}