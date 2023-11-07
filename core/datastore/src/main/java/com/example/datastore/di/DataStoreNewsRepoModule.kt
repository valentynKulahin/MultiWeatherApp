package com.example.datastore.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.datastore.repo.DataStoreRepo
import com.example.datastore.repo.DataStoreRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreNewsRepoModule {

    @Provides
    @Singleton
    fun providesDataStoreRepo(
        dataStore: DataStore<Preferences>
    ): DataStoreRepo {
        return DataStoreRepoImpl(dataStore = dataStore)
    }

}