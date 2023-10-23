package com.example.network.models.news


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class NewsNetworkModel(
    @SerializedName("articles")
    @Expose
    val articles: List<ArticleNetworkModel?>? = null,
    @SerializedName("status")
    @Expose
    val status: String? = null,
    @SerializedName("totalResults")
    @Expose
    val totalResults: Int? = null
)