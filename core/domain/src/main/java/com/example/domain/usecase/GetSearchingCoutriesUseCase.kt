package com.example.domain.usecase

import com.example.data.repo.DataRepo
import com.example.domain.model.country.CountryItemDomainModel
import com.example.domain.util.mapToDomain
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetSearchingCoutriesUseCase @Inject constructor(
    private val dataRepo: DataRepo
) {

    suspend operator fun invoke(country: String): List<CountryItemDomainModel> {
        return dataRepo.getSearchingCountriesList(country = country).mapToDomain()
    }

}