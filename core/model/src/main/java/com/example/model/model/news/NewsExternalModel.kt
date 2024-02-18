package com.example.model.model.news

data class NewsExternalModel(
    val articles: List<ArticleExternalModel?>? = null,
    val status: String? = null,
    val totalResults: Int? = null
)