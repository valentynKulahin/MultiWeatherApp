package com.example.data.di

import com.example.data.repo.location.ILocationServices
import com.example.data.repo.location.ILocationServicesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataLocationModule {

    @Binds
    fun bindsILocationService(
        iLocationServicesRepository: ILocationServicesRepository
    ) : ILocationServices

}