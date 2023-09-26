package com.example.network.di

import com.example.network.BuildConfig
import com.example.network.api.RealTimeApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitNewsModule {

    private const val BASE_URL = "https://newsapi.org/v2"

    @Singleton
    @Provides
    fun provideNewsGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

    @Singleton
    @Provides
    fun provideNewsRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            }
        }
        val client = OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .build()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(provideNewsGson()))
            .baseUrl(BASE_URL)
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideNewsApiService(retrofit: Retrofit): RealTimeApi =
        retrofit.create(RealTimeApi::class.java)

}