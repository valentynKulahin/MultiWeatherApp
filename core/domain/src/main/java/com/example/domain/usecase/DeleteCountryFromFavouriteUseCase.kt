package com.example.domain.usecase

import com.example.data.repo.DataRepo
import com.example.domain.model.country.CountryItemDomainModel
import com.example.domain.util.mapToData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeleteCountryFromFavouriteUseCase @Inject constructor(
    private val dataRepo: DataRepo
) {

    suspend operator fun invoke(countryItemDomainModel: CountryItemDomainModel) {
        dataRepo.deleteCountryFromFavourite(countryItemDataModel = countryItemDomainModel.mapToData())
    }

}