package com.example.network.di.retrofit

import com.example.datastore.repo.DataStoreRepo
import com.example.network.BuildConfig
import com.example.network.api.WeatherApi
import com.example.network.di.WeatherRetrofit
import com.example.network.di.adapter.NetworkResponseAdapterFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
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

    @OptIn(ExperimentalCoroutinesApi::class)
    @WeatherRetrofit
    @Singleton
    @Provides
    fun provideWeatherRetrofit(
        dataStoreRepo: DataStoreRepo
    ): Retrofit = runBlocking(context = Dispatchers.IO) {
        val interceptor = HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            }
        }

        val apiKey = CoroutineScope(context = Dispatchers.IO).async {
            dataStoreRepo.getWeatherToken().first().toString()
        }.await()

        val client = OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .addInterceptor(QueryParameterInterceptor("key", apiKey))
            .build()

        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(provideWeatherGson()))
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .baseUrl(BASE_URL)
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideWeatherApiService(@WeatherRetrofit weatherRetrofit: Retrofit): WeatherApi =
        weatherRetrofit.create(WeatherApi::class.java)

}