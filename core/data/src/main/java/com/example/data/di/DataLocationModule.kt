package com.example.data.di

import android.content.Context
import com.example.data.repo.location.ILocationServices
import com.example.data.repo.location.ILocationServicesImpl
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocationModule {

    @Singleton
    @Provides
    fun provideLocationClient(
        @ApplicationContext context: Context
    ): ILocationServices = ILocationServicesImpl(
        context,
        LocationServices.getFusedLocationProviderClient(context)
    )

}