package com.example.domain.usecase

import com.example.data.repo.DataRepo
import com.example.domain.model.news.NewsDomainModel
import com.example.domain.util.mapToDomain
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetNewsUseCase @Inject constructor(
    private val dataRepo: DataRepo
) {

    suspend operator fun invoke(country: String): NewsDomainModel {
        return dataRepo.getTopNews(country = country).mapToDomain()
    }

}