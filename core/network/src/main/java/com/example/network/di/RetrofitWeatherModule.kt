package com.example.network.di

import com.example.network.api.WeatherApi
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
object RetrofitWeatherModule {

    private const val BASE_URL = "https://api.weatherapi.com/v1/"

    @Singleton
    @Provides
    fun provideWeatherGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

    @WeatherRetrofit
    @Singleton
    @Provides
    fun provideWeatherRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor().apply {
//            if (BuildConfig.DEBUG) {
            setLevel(HttpLoggingInterceptor.Level.BODY)
//            }
        }
        val client = OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .build()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(provideWeatherGson()))
            .baseUrl(BASE_URL)
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideWeatherApiService(@WeatherRetrofit weatherRetrofit: Retrofit): WeatherApi =
        weatherRetrofit.create(WeatherApi::class.java)

}