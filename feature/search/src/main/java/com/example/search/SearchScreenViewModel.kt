package com.example.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.network.response.DataResponse
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

    private val _uiState = MutableStateFlow(SearchScreenUiState())
    val uiState = _uiState.asStateFlow()

    private val _errorState = MutableStateFlow(SearchScreenErrorState())
    val errorState = _errorState.asStateFlow()

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

    fun reducer(intent: SearchScreenUiAction) {
        when (intent) {
            is SearchScreenUiAction.GetSearchingCountriesList -> {
                viewModelScope.launch(context = Dispatchers.IO) {
                    _uiState.update { it.copy(isLoading = true) }

                    val countriesList = getSearchingCoutriesUseCase.invoke(
                        country = _uiState.value.searchingName.toString()
                    )

                    when (countriesList) {
                        is DataResponse.Success -> {
                            _uiState.update { it.copy(countriesList = countriesList.body) }
                        }

                        is DataResponse.Error -> {
                            _errorState.update {
                                it.copy(
                                    isError = true,
                                    isErrorResponse = countriesList.error
                                )
                            }
//                                run { return@mapIndexed item }
                        }
                    }
                }

            }

            is SearchScreenUiAction.UpdateCountryForSearch -> {
                viewModelScope.launch(context = Dispatchers.IO) {
                    _uiState.update {
                        it.copy(countryForSearch = intent.searchingItem)
                    }
                }
            }

            is SearchScreenUiAction.GetHistoryOfSearch -> {
                viewModelScope.launch(context = Dispatchers.IO) {
                    getSearchingHistoryUseCase.invoke()
                }
            }

            is SearchScreenUiAction.GetWeatherInCountry -> {
                viewModelScope.launch(context = Dispatchers.IO) {
                    val weatherState = getWeatherUseCase.invoke(country = intent.searchingValue)
                    when (weatherState) {
                        is DataResponse.Success -> {
                            _uiState.update {
                                it.copy(
                                    searchingHistoryList = getSearchingHistoryUseCase.invoke(),
                                    currentExternalModel = weatherState.body.current
                                        ?: CurrentExternalModel(),
                                    countryForSearch = CountryItemExternalModel(
                                        country = weatherState.body.location?.country,
                                        lat = weatherState.body.location?.lat,
                                        lon = weatherState.body.location?.lon,
                                        name = weatherState.body.location?.name,
                                        region = weatherState.body.location?.region
                                    )
                                )
                            }
                        }

                        is DataResponse.Error -> {
                            _errorState.update {
                                it.copy(
                                    isError = true,
                                    isErrorResponse = weatherState.error
                                )
                            }
//                                run { return@mapIndexed item }
                        }
                    }
                }
            }

            is SearchScreenUiAction.GetWeatherInCountryByLatLon -> {
                viewModelScope.launch(context = Dispatchers.IO) {
                    val weatherState =
                        getWeatherByLatLonUseCase.invoke(latLon = "${intent.latLon.latitude}, ${intent.latLon.longitude}")
                    when (weatherState) {
                        is DataResponse.Success -> {
                            _uiState.update {
                                it.copy(
                                    searchingHistoryList = getSearchingHistoryUseCase.invoke(),
                                    currentExternalModel = weatherState.body.current
                                        ?: CurrentExternalModel(),
                                    countryForSearch = CountryItemExternalModel(
                                        country = weatherState.body.location?.country,
                                        lat = weatherState.body.location?.lat,
                                        lon = weatherState.body.location?.lon,
                                        name = weatherState.body.location?.name,
                                        region = weatherState.body.location?.region
                                    ),
                                    searchingName = weatherState.body.location?.name
                                )
                            }
                        }

                        is DataResponse.Error -> {
                            _errorState.update {
                                it.copy(
                                    isError = true,
                                    isErrorResponse = weatherState.error
                                )
                            }
//                                run { return@mapIndexed item }
                        }
                    }
                }
            }

            is SearchScreenUiAction.UpdateSearchingName -> {
                viewModelScope.launch(context = Dispatchers.IO) {
                    _uiState.update {
                        it.copy(searchingName = intent.seachingValue)
                    }
                }
            }

            is SearchScreenUiAction.NavigateBack -> {
                appNavigator.tryNavigateBack()
            }

            is SearchScreenUiAction.AddToFavourite -> {
                viewModelScope.launch(context = Dispatchers.IO) {
                    addCountryToFavouriteUseCase.invoke(countryItemExternalModel = uiState.value.countryForSearch)
                    _uiState.update { it.copy(countryInFavourite = true) }
                }
            }

            is SearchScreenUiAction.DeleteFromFavourite -> {
                viewModelScope.launch(context = Dispatchers.IO) {
                    deleteCountryFromFavouriteUseCase.invoke(countryItemExternalModel = uiState.value.countryForSearch)
                    _uiState.update { it.copy(countryInFavourite = false) }
                }
            }

            is SearchScreenUiAction.UpdateCountryInFavouriteValue -> {
                viewModelScope.launch(context = Dispatchers.IO) {
                    _uiState.update {
                        it.copy(countryInFavourite = intent.value)
                    }
                }
            }
        }
    }

}