package com.example.search

import com.example.common.model.search.SearchResultItem
import com.example.domain.model.weather.CurrentDomainModel
import com.google.android.gms.maps.model.LatLng

data class SearchScreenContract(
    val searchingName: String? = null,
    val countriesList: List<SearchResultItem> = emptyList(),
    val searchingHistoryList: List<SearchResultItem> = emptyList(),
    val cityValue: SearchResultItem = SearchResultItem(),
    val currentDomainModel: CurrentDomainModel = CurrentDomainModel()
)

sealed class SearchScreenIntent {

    data object GetSearchingCountriesList : SearchScreenIntent()

    data class GetWeatherInCountry(val searchingValue: String) : SearchScreenIntent()

    data class GetWeatherInCountryByLatLon(val latLon: LatLng) : SearchScreenIntent()

    data object GetHistoryOfSearch : SearchScreenIntent()

    data class UpdateSearchingName(val seachingValue: String) : SearchScreenIntent()

    data class UpdateCityValue(val searchingItem: SearchResultItem) : SearchScreenIntent()

    data object NavigateBack : SearchScreenIntent()

}