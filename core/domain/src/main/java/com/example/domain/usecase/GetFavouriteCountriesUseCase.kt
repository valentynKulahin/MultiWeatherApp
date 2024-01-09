package com.example.domain.usecase

import com.example.data.repo.DataRepo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetFavouriteCountriesUseCase @Inject constructor(
    private val dataRepo: DataRepo
) {

    suspend operator fun invoke() {

    }

}