package com.example.network.api

import com.example.network.models.news.NewsNetworkModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NewsApi {

    @Headers("Content-Type: application/json")
    @GET(value = "top-headlines")
    fun getTopHeadlineNews(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Call<NewsNetworkModel>

}