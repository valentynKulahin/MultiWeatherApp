package com.example.data.di

import com.example.data.util.NetworkMonitor
import com.example.data.util.NetworkMonitorRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsNetworkMonitor(
        networkMonitor: NetworkMonitorRepository,
    ): NetworkMonitor

}