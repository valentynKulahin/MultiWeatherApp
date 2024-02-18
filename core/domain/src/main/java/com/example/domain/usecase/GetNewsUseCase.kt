package com.example.domain.usecase

import com.example.data.repo.news.NewsDataRemoteRepo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetNewsUseCase @Inject constructor(
    private val newsDataRemoteRepo: NewsDataRemoteRepo
) {

    suspend operator fun invoke(country: String) =
        newsDataRemoteRepo.getTopNews(country = country)

}