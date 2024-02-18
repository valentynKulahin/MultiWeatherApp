package com.example.data.util.mappers

import com.example.model.model.news.ArticleExternalModel
import com.example.model.model.news.NewsExternalModel
import com.example.model.model.news.SourceExternalModel
import com.example.network.models.news.ArticleNetworkModel
import com.example.network.models.news.NewsNetworkModel
import com.example.network.models.news.SourceNetworkModel

fun NewsNetworkModel.mapToData(): NewsExternalModel {
    return NewsExternalModel(articles.mapToData(), status, totalResults)
}

@JvmName(name = "listArticleNetworkModelToData")
fun List<ArticleNetworkModel?>?.mapToData(): List<ArticleExternalModel> {
    return mutableListOf<ArticleExternalModel>().apply {
        this@mapToData?.forEach {
            this.add(it.mapToData())
        }
    }
}

@JvmName(name = "articleNetworkModelToData")
fun ArticleNetworkModel?.mapToData(): ArticleExternalModel {
    return ArticleExternalModel(
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
fun SourceNetworkModel.mapToData(): SourceExternalModel {
    return SourceExternalModel(id, name)
}