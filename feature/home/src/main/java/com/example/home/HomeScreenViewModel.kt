package com.example.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.country.CountryItemDomainModel
import com.example.domain.usecase.DeleteCountryFromFavouriteUseCase
import com.example.domain.usecase.GetFavouriteCountriesUseCase
import com.example.domain.usecase.GetLocationUseCase
import com.example.domain.usecase.GetWeatherUseCase
import com.example.domain.usecase.GetNewsUseCase
import com.example.domain.usecase.GetWeatherByLatLonUseCase
import com.example.navi.repo.AppNavigator
import com.example.navi.util.WeatherDestinations
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val appNavigator: AppNavigator,
    private val getWeatherUseCase: GetWeatherUseCase,
    private val getWeatherByLatLonUseCase: GetWeatherByLatLonUseCase,
    private val getNewsUseCase: GetNewsUseCase,
    private val getFavouriteCountriesUseCase: GetFavouriteCountriesUseCase,
    private val deleteCountryFromFavouriteUseCase: DeleteCountryFromFavouriteUseCase,
    private val getLocationUseCase: GetLocationUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeScreenContract())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch(context = Dispatchers.IO) {
            _uiState.update { it.copy(loading = true) }

            getLocationUseCase.invoke().collect { location ->
                _uiState.update { it.copy(myLocation = location) }
            }

            getFavouriteCountriesUseCase.invoke().collect { list ->
                val listWithWeather = list.map {
                    it.copy(
                        weather = getWeatherUseCase.invoke(country = it.name.toString()),
                        news = getNewsUseCase.invoke(country = getCountryCode(it.name.toString()))
                    )
                }

                val mutableListWithWeather = listWithWeather.toMutableList()
                val myLocationCountry = CountryItemDomainModel(
                    name = "My location",
                    weather = getWeatherByLatLonUseCase.invoke(latLon = "${_uiState.value.myLocation?.latitude}, ${_uiState.value.myLocation?.longitude}"),
                    news = getNewsUseCase.invoke(country = "us")
                )
                mutableListWithWeather.add(
                    index = 0,
                    element = myLocationCountry
                )

                _uiState.emit(
                    _uiState.value.copy(
                        favouritesCountry = mutableListWithWeather.toList().sortedBy { it.id }
                    )
                )
            }

            _uiState.update { it.copy(loading = false) }
        }
    }

    fun reducer(intent: HomeScreenIntent) {
        when (intent) {
            is HomeScreenIntent.NavigateToSearchScreen -> {
                appNavigator.tryNavigateTo(route = WeatherDestinations.SearchScreen.route)
            }

            is HomeScreenIntent.DeleteFromFavourite -> {
                viewModelScope.launch(context = Dispatchers.IO) {
                    deleteCountryFromFavouriteUseCase.invoke(countryItemDomainModel = intent.countryItemDomainModel)
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