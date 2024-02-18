package com.example.model.model.news

data class ArticleExternalModel(
    val author: String? = null,
    val content: String? = null,
    val description: String? = null,
    val publishedAt: String? = null,
    val source: SourceExternalModel? = null,
    val title: String? = null,
    val url: String? = null,
    val urlToImage: String? = null
)