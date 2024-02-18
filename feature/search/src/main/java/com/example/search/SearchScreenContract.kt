package com.example.search

import com.example.common.network.status.NetworkStatus
import com.example.model.model.country.CountryItemExternalModel
import com.example.model.model.weather.CurrentExternalModel
import com.google.android.gms.maps.model.LatLng

data class SearchScreenContract(
    val searchingName: String? = null,
    val countriesList: List<CountryItemExternalModel> = emptyList(),
    val searchingHistoryList: List<CountryItemExternalModel> = emptyList(),
    val countryForSearch: CountryItemExternalModel = CountryItemExternalModel(),
    val currentExternalModel: CurrentExternalModel = CurrentExternalModel(),
    val countryInFavourite: Boolean = false,
    val networkStatus: NetworkStatus = NetworkStatus.Unknown
)

sealed class SearchScreenIntent {

    data object GetSearchingCountriesList : SearchScreenIntent()

    data class GetWeatherInCountry(val searchingValue: String) : SearchScreenIntent()

    data class GetWeatherInCountryByLatLon(val latLon: LatLng) : SearchScreenIntent()

    data object GetHistoryOfSearch : SearchScreenIntent()

    data class UpdateSearchingName(val seachingValue: String) : SearchScreenIntent()

    data class UpdateCountryForSearch(val searchingItem: CountryItemExternalModel) : SearchScreenIntent()

    data object NavigateBack : SearchScreenIntent()

    data object AddToFavourite : SearchScreenIntent()

    data object DeleteFromFavourite : SearchScreenIntent()

    data class UpdateCountryInFavouriteValue(val value: Boolean) : SearchScreenIntent()

}