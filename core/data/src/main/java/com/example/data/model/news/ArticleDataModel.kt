package com.example.data.model.news

data class ArticleDataModel(
    val author: String? = null,
    val content: String? = null,
    val description: String? = null,
    val publishedAt: String? = null,
    val source: SourceDataModel? = null,
    val title: String? = null,
    val url: String? = null,
    val urlToImage: String? = null
)