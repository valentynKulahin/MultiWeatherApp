package com.example.network.di.retrofit

import com.example.datastore.repo.DataStoreRepo
import com.example.network.BuildConfig
import com.example.network.api.NewsApi
import com.example.network.di.NewsRetrofit
import com.example.network.di.adapter.NetworkResponseAdapterFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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
object RetrofitNewsModule {

    private const val BASE_URL = "https://newsapi.org/v2/"

    @Singleton
    @Provides
    fun provideNewsGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

    @NewsRetrofit
    @Singleton
    @Provides
    fun provideNewsRetrofit(
        dataStoreRepo: DataStoreRepo
    ): Retrofit = runBlocking(context = Dispatchers.IO) {

        val interceptor = HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            }
        }

        val apiKey = CoroutineScope(context = Dispatchers.IO).async {
            dataStoreRepo.getNewsToken().first().toString()
        }.await()

        val client = OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .addInterceptor(QueryParameterInterceptor("apiKey", apiKey))
            .build()

        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(provideNewsGson()))
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .baseUrl(BASE_URL)
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideNewsApiService(@NewsRetrofit newsRetrofit: Retrofit): NewsApi =
        newsRetrofit.create(NewsApi::class.java)

}