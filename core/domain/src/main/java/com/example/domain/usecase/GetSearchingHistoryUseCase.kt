package com.example.domain.usecase

import com.example.model.model.country.CountryItemExternalModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetSearchingHistoryUseCase @Inject constructor(

) {

    operator fun invoke(): List<CountryItemExternalModel> {
        return emptyList()
    }

}