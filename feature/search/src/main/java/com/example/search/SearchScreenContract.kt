package com.example.search

import com.example.domain.model.country.CountryItemDomainModel
import com.example.domain.model.weather.CurrentDomainModel
import com.google.android.gms.maps.model.LatLng

data class SearchScreenContract(
    val searchingName: String? = null,
    val countriesList: List<CountryItemDomainModel> = emptyList(),
    val searchingHistoryList: List<CountryItemDomainModel> = emptyList(),
    val countryForSearch: CountryItemDomainModel = CountryItemDomainModel(),
    val currentDomainModel: CurrentDomainModel = CurrentDomainModel(),
    val countryInFavourite: Boolean = false
)

sealed class SearchScreenIntent {

    data object GetSearchingCountriesList : SearchScreenIntent()

    data class GetWeatherInCountry(val searchingValue: String) : SearchScreenIntent()

    data class GetWeatherInCountryByLatLon(val latLon: LatLng) : SearchScreenIntent()

    data object GetHistoryOfSearch : SearchScreenIntent()

    data class UpdateSearchingName(val seachingValue: String) : SearchScreenIntent()

    data class UpdateCountryForSearch(val searchingItem: CountryItemDomainModel) : SearchScreenIntent()

    data object NavigateBack : SearchScreenIntent()

    data object AddToFavourite : SearchScreenIntent()

    data object DeleteFromFavourite : SearchScreenIntent()

    data class UpdateCountryInFavouriteValue(val value: Boolean) : SearchScreenIntent()

}