package com.example.search

import com.example.common.network.response.DataResponseError
import com.example.common.network.status.NetworkStatus
import com.example.model.model.country.CountryItemExternalModel
import com.example.model.model.weather.CurrentExternalModel
import com.google.android.gms.maps.model.LatLng

data class SearchScreenUiState(
    val favouriteCountries: List<CountryItemExternalModel> = emptyList(),
    val searchingName: String? = null,
    val countriesList: List<CountryItemExternalModel> = emptyList(),
    val searchingHistoryList: List<CountryItemExternalModel> = emptyList(),
    val countryForSearch: CountryItemExternalModel = CountryItemExternalModel(),
    val currentExternalModel: CurrentExternalModel = CurrentExternalModel(),
    val networkStatus: NetworkStatus = NetworkStatus.Unknown,
    val isLoading: Boolean = false
)

sealed class SearchScreenUiAction {

    data object GetSearchingCountriesList : SearchScreenUiAction()

    data class GetWeatherInCountry(val searchingValue: String) : SearchScreenUiAction()

    data class GetWeatherInCountryByLatLon(val latLon: LatLng) : SearchScreenUiAction()

    data object GetHistoryOfSearch : SearchScreenUiAction()

    data class UpdateSearchingName(val searchingValue: String) : SearchScreenUiAction()

    data class UpdateCountryForSearch(val searchingItem: CountryItemExternalModel) : SearchScreenUiAction()

    data object NavigateBack : SearchScreenUiAction()

    data object AddToFavourite : SearchScreenUiAction()

    data object DeleteFromFavourite : SearchScreenUiAction()

}

data class SearchScreenErrorState(
    val isError: Boolean = false,
    val isErrorResponse: DataResponseError = DataResponseError(0, "")
)