package com.example.domain.usecase

import com.example.domain.model.news.NewsDomainModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetNewsUseCase @Inject constructor(

) {

    operator fun invoke(): NewsDomainModel {
        return NewsDomainModel()
    }

}