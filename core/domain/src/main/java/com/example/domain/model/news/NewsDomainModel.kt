package com.example.domain.model.news

data class NewsDomainModel(
    val articles: List<ArticleDomainModel?>? = null,
    val status: String? = null,
    val totalResults: Int? = null
)