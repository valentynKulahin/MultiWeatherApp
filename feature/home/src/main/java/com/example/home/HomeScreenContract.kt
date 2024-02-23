package com.example.home

import com.example.common.network.response.DataResponseError
import com.example.common.network.status.NetworkStatus
import com.example.model.model.country.CountryItemExternalModel
import com.google.android.gms.maps.model.LatLng

data class HomeScreenUiState(
    val favouriteCountries: List<CountryItemExternalModel> = emptyList(),
    val isLoading: Boolean = false,
    val myLocation: LatLng? = null,
    val networkStatus: NetworkStatus = NetworkStatus.Unknown
)

sealed interface HomeScreenUiAction {

    data object NavigateToSearchScreen : HomeScreenUiAction

    data object UpdateWeatherAndNews : HomeScreenUiAction

    data class DeleteFromFavourite(val countryItemExternalModel: CountryItemExternalModel) : HomeScreenUiAction

}

data class HomeScreenErrorState(
    val isWeatherError: Boolean = false,
    val isWeatherErrorResponse: DataResponseError = DataResponseError(0, ""),
    val isNewsError: Boolean = false,
    val isNewsErrorResponse: DataResponseError = DataResponseError(0, ""),
)
