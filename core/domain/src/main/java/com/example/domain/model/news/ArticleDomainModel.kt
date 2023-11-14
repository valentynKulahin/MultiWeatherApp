package com.example.domain.model.news

data class ArticleDomainModel(
    val author: String? = null,
    val content: String? = null,
    val description: String? = null,
    val publishedAt: String? = null,
    val source: SourceDomainModel? = null,
    val title: String? = null,
    val url: String? = null,
    val urlToImage: String? = null
)