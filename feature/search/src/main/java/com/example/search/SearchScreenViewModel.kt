package com.example.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.network.status.NetworkStatus
import com.example.domain.usecase.AddCountryToFavouriteUseCase
import com.example.domain.usecase.DeleteCountryFromFavouriteUseCase
import com.example.domain.usecase.GetNetworkStatusUseCase
import com.example.domain.usecase.GetSearchingCoutriesUseCase
import com.example.domain.usecase.GetSearchingHistoryUseCase
import com.example.domain.usecase.GetWeatherByLatLonUseCase
import com.example.domain.usecase.GetWeatherUseCase
import com.example.model.model.country.CountryItemExternalModel
import com.example.model.model.weather.CurrentExternalModel
import com.example.model.model.weather.WeatherExternalModel
import com.example.navi.repo.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val getWeatherByLatLonUseCase: GetWeatherByLatLonUseCase,
    private val getSearchingCoutriesUseCase: GetSearchingCoutriesUseCase,
    private val appNavigator: AppNavigator,
    private val getSearchingHistoryUseCase: GetSearchingHistoryUseCase,
    private val addCountryToFavouriteUseCase: AddCountryToFavouriteUseCase,
    private val deleteCountryFromFavouriteUseCase: DeleteCountryFromFavouriteUseCase,
    private val getNetworkStatusUseCase: GetNetworkStatusUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchScreenContract())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch(context = Dispatchers.IO) {
            _uiState.update {
                it.copy(
                    networkStatus = getNetworkStatusUseCase.invoke().stateIn(
                        scope = this,
                        started = SharingStarted.WhileSubscribed(5_000),
                        initialValue = NetworkStatus.Unknown
                    ).value
                )
            }
        }
    }

    fun reducer(intent: SearchScreenIntent) {
        when (intent) {
            is SearchScreenIntent.GetSearchingCountriesList -> {
                viewModelScope.launch(context = Dispatchers.IO) {
                    _uiState.update {
                        it.copy(
//                            countriesList = getSearchingCoutriesUseCase.invoke(
//                                country = _uiState.value.searchingName.toString()
//                            )
                            countriesList = emptyList()
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
                        val weatherExternalModel = WeatherExternalModel()
//                            getWeatherUseCase.invoke(country = intent.searchingValue)
                        it.copy(
                            searchingHistoryList = getSearchingHistoryUseCase.invoke(),
                            currentExternalModel = weatherExternalModel.current ?: CurrentExternalModel(),
                            countryForSearch = CountryItemExternalModel(
                                country = weatherExternalModel.location?.country,
                                lat = weatherExternalModel.location?.lat,
                                lon = weatherExternalModel.location?.lon,
                                name = weatherExternalModel.location?.name,
                                region = weatherExternalModel.location?.region
                            )
                        )
                    }
                }
            }

            is SearchScreenIntent.GetWeatherInCountryByLatLon -> {
                viewModelScope.launch(context = Dispatchers.IO) {
                    _uiState.update {
                        val weatherExternalModel = WeatherExternalModel()
//                            getWeatherByLatLonUseCase.invoke(latLon = "${intent.latLon.latitude}, ${intent.latLon.longitude}")
                        it.copy(
                            searchingHistoryList = getSearchingHistoryUseCase.invoke(),
                            currentExternalModel = weatherExternalModel.current ?: CurrentExternalModel(),
                            countryForSearch = CountryItemExternalModel(
                                country = weatherExternalModel.location?.country,
                                lat = weatherExternalModel.location?.lat,
                                lon = weatherExternalModel.location?.lon,
                                name = weatherExternalModel.location?.name,
                                region = weatherExternalModel.location?.region
                            ),
                            searchingName = weatherExternalModel.location?.name
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
                    addCountryToFavouriteUseCase.invoke(countryItemExternalModel = uiState.value.countryForSearch)
                    _uiState.update { it.copy(countryInFavourite = true) }
                }
            }

            is SearchScreenIntent.DeleteFromFavourite -> {
                viewModelScope.launch(context = Dispatchers.IO) {
                    deleteCountryFromFavouriteUseCase.invoke(countryItemExternalModel = uiState.value.countryForSearch)
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