package com.example.search

import com.example.common.model.search.SearchResultItem

data class SearchScreenContract(
    val searchingName: String? = null,
    val countriesList: List<SearchResultItem> = emptyList(),
    val searchingHistoryList: List<SearchResultItem> = emptyList()
)

sealed class SearchScreenIntent {

    data object GetSearchingCountriesList : SearchScreenIntent()

    data class GetWeatherInCountry(val country: String) : SearchScreenIntent()

    data class UpdateSearchingName(val country: String) : SearchScreenIntent()

}