package com.example.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.network.response.DataResponse
import com.example.domain.usecase.DeleteCountryFromFavouriteUseCase
import com.example.domain.usecase.GetFavouriteCountriesUseCase
import com.example.domain.usecase.GetLocationUseCase
import com.example.domain.usecase.GetNetworkStatusUseCase
import com.example.domain.usecase.GetWeatherUseCase
import com.example.domain.usecase.GetNewsUseCase
import com.example.domain.usecase.GetWeatherByLatLonUseCase
import com.example.model.model.country.CountryItemExternalModel
import com.example.navi.repo.AppNavigator
import com.example.navi.util.WeatherDestinations
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    getFavouriteCountriesUseCase: GetFavouriteCountriesUseCase,
    getLocationUseCase: GetLocationUseCase,
    getNetworkStatusUseCase: GetNetworkStatusUseCase,
    private val appNavigator: AppNavigator,
    private val getWeatherUseCase: GetWeatherUseCase,
    private val getWeatherByLatLonUseCase: GetWeatherByLatLonUseCase,
    private val getNewsUseCase: GetNewsUseCase,
    private val deleteCountryFromFavouriteUseCase: DeleteCountryFromFavouriteUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeScreenUiState())
    val uiState = _uiState.asStateFlow()

    init {
        combine(
            getLocationUseCase.invoke(),
            getFavouriteCountriesUseCase.invoke(),
            getNetworkStatusUseCase.invoke()
        ) { location, countriesList, networkStatus ->

            val mutableListWithWeather = countriesList.toMutableList()
            val myLocationCountry = CountryItemExternalModel(
                name = "My location",
                lat = location?.latitude,
                lon = location?.longitude
            )
            mutableListWithWeather.add(
                index = 0,
                element = myLocationCountry
            )

            _uiState.update {
                it.copy(
                    favouritesCountry = mutableListWithWeather.toList().sortedBy { it.id },
                    isLoading = false,
                    myLocation = location,
                    networkStatus = networkStatus
                )
            }
            HomeScreenUiAction.UpdateWeatherAndNews
        }
            .onStart {
                _uiState.update { it.copy(isLoading = true) }
            }
            .onEach(::reducer)
            .flowOn(context = Dispatchers.IO)
            .launchIn(viewModelScope)
    }

    fun reducer(action: HomeScreenUiAction) {
        when (action) {
            is HomeScreenUiAction.NavigateToSearchScreen -> {
                appNavigator.tryNavigateTo(route = WeatherDestinations.SearchScreen.route)
            }

            is HomeScreenUiAction.DeleteFromFavourite -> {
                viewModelScope.launch(context = Dispatchers.IO) {
                    deleteCountryFromFavouriteUseCase.invoke(countryItemExternalModel = action.countryItemExternalModel)
                }
            }

            is HomeScreenUiAction.UpdateWeatherAndNews -> {
                viewModelScope.launch(context = Dispatchers.IO) {
                    val newList = arrayListOf<CountryItemExternalModel>()
                    _uiState.value.favouritesCountry.map { item ->
                        val weatherState = if (item.id == 0L) getWeatherByLatLonUseCase.invoke(
                            latLon = "${_uiState.value.myLocation?.latitude}, ${_uiState.value.myLocation?.longitude}"
                        ) else getWeatherUseCase.invoke(country = item.name.toString())

                        when (weatherState) {
                            is DataResponse.Success -> {
                                newList.add(item.copy(weather = weatherState.body))
                            }

                            is DataResponse.Error -> {
                                _uiState.update {
                                    it.copy(
                                        isError = true,
                                        error = weatherState.error
                                    )
                                }
                                run { return@map item }
                            }
                        }

                        val newsState =
                            getNewsUseCase.invoke(country = getCountryCode(item.name.toString()))
                        when (newsState) {
                            is DataResponse.Success -> {
                                newList.add(item.copy(news = newsState.body))
                            }

                            is DataResponse.Error -> {
                                _uiState.update {
                                    it.copy(
                                        isError = true,
                                        error = newsState.error
                                    )
                                }
                                run { return@map item }
                            }
                        }
                    }

                    _uiState.update { it.copy(favouritesCountry = newList) }
                }
            }
        }
    }

    private fun getCountryCode(countryName: String): String {
        val locale = Locale.getAvailableLocales()
            .firstOrNull { it.displayCountry.equals(countryName, ignoreCase = true) }
        return if (locale == null) "us" else locale.country
    }
}