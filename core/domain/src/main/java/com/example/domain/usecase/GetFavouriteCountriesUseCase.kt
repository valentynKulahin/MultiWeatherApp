package com.example.domain.usecase

import com.example.data.repo.DataRepo
import com.example.data.util.mappers.mapToData
import com.example.domain.model.country.CountryItemDomainModel
import com.example.domain.util.mapToDomain
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetFavouriteCountriesUseCase @Inject constructor(
    private val dataRepo: DataRepo
) {

    operator fun invoke(): Flow<List<CountryItemDomainModel>> {
        return dataRepo.getFavouriteCountries().mapToDomain()
    }

}