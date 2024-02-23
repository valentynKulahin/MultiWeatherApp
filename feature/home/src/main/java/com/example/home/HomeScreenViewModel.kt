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
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn

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

    private val _errorState = MutableStateFlow(HomeScreenErrorState())
    val errorState = _errorState.asStateFlow()

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
                    favouriteCountries = mutableListWithWeather.toList().sortedBy { it.id },
                    myLocation = location,
                    networkStatus = networkStatus
                )
            }

            reducer(HomeScreenUiAction.UpdateWeatherAndNews)
        }.onStart {
            _uiState.update { it.copy(isLoading = true) }
        }.flowOn(context = Dispatchers.IO).launchIn(scope = viewModelScope)
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
                    _uiState.update { it.copy(isLoading = true) }
                    _errorState.update { it.copy(isWeatherError = false, isNewsError = false) }

                    val newList = arrayListOf<CountryItemExternalModel>()
                    _uiState.value.favouriteCountries.mapIndexed { index, item ->
                        var newWeather = item.weather.copy()
                        var newNews = item.news.copy()

                        val weatherState = if (index == 0) getWeatherByLatLonUseCase.invoke(
                            latLon = "${_uiState.value.myLocation?.latitude}, ${_uiState.value.myLocation?.longitude}"
                        ) else getWeatherUseCase.invoke(country = item.name.toString())

                        when (weatherState) {
                            is DataResponse.Success -> {
//                                newList.add(item.copy(weather = weatherState.body))
                                newWeather = weatherState.body
                            }

                            is DataResponse.Error -> {
                                _errorState.update {
                                    it.copy(
                                        isWeatherError = true,
                                        isWeatherErrorResponse = weatherState.error
                                    )
                                }
//                                run { return@mapIndexed item }
                            }
                        }

                        val newsState =
                            getNewsUseCase.invoke(country = getCountryCode(item.name.toString()))
                        when (newsState) {
                            is DataResponse.Success -> {
//                                newList.add(item.copy(news = newsState.body))
                                newNews = newsState.body
                            }

                            is DataResponse.Error -> {
                                _errorState.update {
                                    it.copy(
                                        isNewsError = true,
                                        isNewsErrorResponse = newsState.error
                                    )
                                }
//                                run { return@mapIndexed item }
                            }
                        }
                        newList.add(item.copy(weather = newWeather, news = newNews))
                    }
                    _uiState.update {
                        it.copy(
                            favouriteCountries = newList.toList(),
                            isLoading = false
                        )
                    }
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