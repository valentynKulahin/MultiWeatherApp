package com.example.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.country.CountryItemDomainModel
import com.example.domain.model.weather.CurrentDomainModel
import com.example.domain.usecase.AddCountryToFavouriteUseCase
import com.example.domain.usecase.DeleteCountryFromFavouriteUseCase
import com.example.domain.usecase.GetSearchingCoutriesUseCase
import com.example.domain.usecase.GetSearchingHistoryUseCase
import com.example.domain.usecase.GetWeatherByLatLonUseCase
import com.example.domain.usecase.GetWeatherUseCase
import com.example.navi.repo.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val appNavigator: AppNavigator,
    private val getSearchingCoutriesUseCase: GetSearchingCoutriesUseCase,
    private val getSearchingHistoryUseCase: GetSearchingHistoryUseCase,
    private val getWeatherUseCase: GetWeatherUseCase,
    private val getWeatherByLatLonUseCase: GetWeatherByLatLonUseCase,
    private val addCountryToFavouriteUseCase: AddCountryToFavouriteUseCase,
    private val deleteCountryFromFavouriteUseCase: DeleteCountryFromFavouriteUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchScreenContract())
    val uiState = _uiState.asStateFlow()

    fun reducer(intent: SearchScreenIntent) {
        when (intent) {
            is SearchScreenIntent.GetSearchingCountriesList -> {
                viewModelScope.launch(context = Dispatchers.IO) {
                    _uiState.update {
                        it.copy(
                            countriesList = getSearchingCoutriesUseCase.invoke(
                                country = _uiState.value.searchingName.toString()
                            )
                        )
                    }
                }
            }

            is SearchScreenIntent.UpdateCountryForSearch -> {
                viewModelScope.launch(context = Dispatchers.IO) {
                    _uiState.update {
                        it.copy(countryForSearch = intent.searchingItem)
                    }
                }
            }

            is SearchScreenIntent.GetHistoryOfSearch -> {
                viewModelScope.launch(context = Dispatchers.IO) {
                    getSearchingHistoryUseCase.invoke()
                }
            }

            is SearchScreenIntent.GetWeatherInCountry -> {
                viewModelScope.launch(context = Dispatchers.IO) {
                    _uiState.update {
                        val weatherDomainModel =
                            getWeatherUseCase.invoke(country = intent.searchingValue)
                        it.copy(
                            searchingHistoryList = getSearchingHistoryUseCase.invoke(),
                            currentDomainModel = weatherDomainModel.current ?: CurrentDomainModel(),
                            countryForSearch = CountryItemDomainModel(
                                country = weatherDomainModel.location?.country,
                                lat = weatherDomainModel.location?.lat,
                                lon = weatherDomainModel.location?.lon,
                                name = weatherDomainModel.location?.name,
                                region = weatherDomainModel.location?.region
                            )
                        )
                    }
                }
            }

            is SearchScreenIntent.GetWeatherInCountryByLatLon -> {
                viewModelScope.launch(context = Dispatchers.IO) {
                    _uiState.update {
                        val weatherDomainModel =
                            getWeatherByLatLonUseCase.invoke(latLon = "${intent.latLon.latitude}, ${intent.latLon.longitude}")
                        it.copy(
                            searchingHistoryList = getSearchingHistoryUseCase.invoke(),
                            currentDomainModel = weatherDomainModel.current ?: CurrentDomainModel(),
                            countryForSearch = CountryItemDomainModel(
                                country = weatherDomainModel.location?.country,
                                lat = weatherDomainModel.location?.lat,
                                lon = weatherDomainModel.location?.lon,
                                name = weatherDomainModel.location?.name,
                                region = weatherDomainModel.location?.region
                            ),
                            searchingName = weatherDomainModel.location?.name
                        )
                    }
                }
            }

            is SearchScreenIntent.UpdateSearchingName -> {
                viewModelScope.launch(context = Dispatchers.IO) {
                    _uiState.update {
                        it.copy(searchingName = intent.seachingValue)
                    }
                }
            }

            is SearchScreenIntent.NavigateBack -> {
                appNavigator.tryNavigateBack()
            }

            is SearchScreenIntent.AddToFavourite -> {
                viewModelScope.launch(context = Dispatchers.IO) {
                    addCountryToFavouriteUseCase.invoke(countryItemDomainModel = uiState.value.countryForSearch)
                    _uiState.update { it.copy(countryInFavourite = true) }
                }
            }

            is SearchScreenIntent.DeleteFromFavourite -> {
                viewModelScope.launch(context = Dispatchers.IO) {
                    deleteCountryFromFavouriteUseCase.invoke(countryItemDomainModel = uiState.value.countryForSearch)
                    _uiState.update { it.copy(countryInFavourite = false) }
                }
            }

            is SearchScreenIntent.UpdateCountryInFavouriteValue -> {
                viewModelScope.launch(context = Dispatchers.IO) {
                    _uiState.update {
                        it.copy(countryInFavourite = intent.value)
                    }
                }
            }
        }
    }

}