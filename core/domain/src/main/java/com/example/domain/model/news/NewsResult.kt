package com.example.domain.model.news

data class NewsResult(
    val articles: List<Article?>? = null,
    val status: String? = null,
    val totalResults: Int? = null
)