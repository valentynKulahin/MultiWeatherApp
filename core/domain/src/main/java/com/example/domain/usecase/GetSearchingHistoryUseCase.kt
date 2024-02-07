package com.example.domain.usecase

import com.example.data.repo.DataRepo
import com.example.domain.model.country.CountryItemDomainModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetSearchingHistoryUseCase @Inject constructor(
    private val dataRepo: DataRepo
) {

    suspend operator fun invoke(): List<CountryItemDomainModel> {
        return emptyList()
    }

}