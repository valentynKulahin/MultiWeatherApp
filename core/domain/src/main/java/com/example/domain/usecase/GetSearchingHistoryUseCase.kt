package com.example.domain.usecase

import com.example.common.model.search.SearchingResult
import com.example.data.repo.DataRepo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetSearchingHistoryUseCase @Inject constructor(
    private val dataRepo: DataRepo
) {

    suspend operator fun invoke(): SearchingResult {
        return SearchingResult()
    }

}