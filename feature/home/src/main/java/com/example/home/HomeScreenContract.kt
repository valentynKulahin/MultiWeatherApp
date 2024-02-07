package com.example.home

import com.example.domain.model.country.CountryItemDomainModel
import com.example.domain.model.news.NewsDomainModel
import com.example.domain.model.weather.WeatherDomainModel
import com.google.android.gms.maps.model.LatLng

data class HomeScreenContract(
    val favouritesCountry: List<CountryItemDomainModel> = emptyList(),
    val loading: Boolean = false,
    val myLocation: LatLng? = null
)

sealed interface HomeScreenIntent {

    data object NavigateToSearchScreen : HomeScreenIntent

    data class DeleteFromFavourite(val countryItemDomainModel: CountryItemDomainModel) : HomeScreenIntent

}
