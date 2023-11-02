package com.example.data.model.news

data class NewsDataModel(
    val articles: List<ArticleDataModel?>? = null,
    val status: String? = null,
    val totalResults: Int? = null
)