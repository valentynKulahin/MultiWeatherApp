package com.example.network.models.news


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class ArticleNetworkModel(
    @SerializedName("author")
    @Expose
    val author: String? = null,
    @SerializedName("content")
    @Expose
    val content: String? = null,
    @SerializedName("description")
    @Expose
    val description: String? = null,
    @SerializedName("publishedAt")
    @Expose
    val publishedAt: String? = null,
    @SerializedName("source")
    @Expose
    val source: SourceNetworkModel? = null,
    @SerializedName("title")
    @Expose
    val title: String? = null,
    @SerializedName("url")
    @Expose
    val url: String? = null,
    @SerializedName("urlToImage")
    @Expose
    val urlToImage: String? = null
)