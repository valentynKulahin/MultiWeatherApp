package com.example.home

import com.example.common.network.response.DataResponseError
import com.example.common.network.status.NetworkStatus
import com.example.model.model.country.CountryItemExternalModel
import com.google.android.gms.maps.model.LatLng

data class HomeScreenUiState(
    val favouritesCountry: List<CountryItemExternalModel> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val error: DataResponseError = DataResponseError(0, ""),
    val myLocation: LatLng? = null,
    val networkStatus: NetworkStatus = NetworkStatus.Unknown
)

sealed interface HomeScreenUiAction {

    data object NavigateToSearchScreen : HomeScreenUiAction

    data object UpdateWeatherAndNews : HomeScreenUiAction

    data class DeleteFromFavourite(val countryItemExternalModel: CountryItemExternalModel) : HomeScreenUiAction

}
