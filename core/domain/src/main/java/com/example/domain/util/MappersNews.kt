package com.example.domain.util

import com.example.data.model.news.ArticleDataModel
import com.example.data.model.news.NewsDataModel
import com.example.data.model.news.SourceDataModel
import com.example.domain.model.news.ArticleDomainModel
import com.example.domain.model.news.NewsDomainModel
import com.example.domain.model.news.SourceDomainModel

fun NewsDataModel.mapToDomain(): NewsDomainModel {
    return NewsDomainModel(articles.mapToDomain(), status, totalResults)
}

@JvmName(name = "listArticleDataModelToDomain")
fun List<ArticleDataModel?>?.mapToDomain(): List<ArticleDomainModel> {
    return mutableListOf<ArticleDomainModel>().apply {
        this@mapToDomain?.forEach {
            this.add(it.mapToDomain())
        }
    }
}

@JvmName(name = "articleDataModelToDomain")
fun ArticleDataModel?.mapToDomain(): ArticleDomainModel {
    return ArticleDomainModel(
        this?.author,
        this?.content,
        this?.description,
        this?.publishedAt,
        this?.source?.mapToDomain(),
        this?.title,
        this?.url,
        this?.urlToImage
    )
}

@JvmName(name = "sourceDataModelToDomain")
fun SourceDataModel.mapToDomain(): SourceDomainModel {
    return SourceDomainModel(id, name)
}