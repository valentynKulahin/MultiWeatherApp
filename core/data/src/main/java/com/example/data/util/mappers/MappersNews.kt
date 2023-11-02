package com.example.data.util.mappers

import com.example.data.model.news.ArticleDataModel
import com.example.data.model.news.NewsDataModel
import com.example.data.model.news.SourceDataModel
import com.example.network.models.news.ArticleNetworkModel
import com.example.network.models.news.NewsNetworkModel
import com.example.network.models.news.SourceNetworkModel

fun NewsNetworkModel.mapToData(): NewsDataModel {
    return NewsDataModel(articles.mapToData(), status, totalResults)
}

@JvmName(name = "listArticleNetworkModelToData")
fun List<ArticleNetworkModel?>?.mapToData(): List<ArticleDataModel> {
    return mutableListOf<ArticleDataModel>().apply {
        this@mapToData?.forEach {
            this.add(it.mapToData())
        }
    }
}

@JvmName(name = "articleNetworkModelToData")
fun ArticleNetworkModel?.mapToData(): ArticleDataModel {
    return ArticleDataModel(
        this?.author,
        this?.content,
        this?.description,
        this?.publishedAt,
        this?.source?.mapToData(),
        this?.title,
        this?.url,
        this?.urlToImage
    )
}

@JvmName(name = "sourceNetworkModelToData")
fun SourceNetworkModel.mapToData(): SourceDataModel {
    return SourceDataModel(id, name)
}