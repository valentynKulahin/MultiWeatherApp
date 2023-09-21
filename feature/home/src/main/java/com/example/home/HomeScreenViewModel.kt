package com.example.home

import androidx.lifecycle.ViewModel
import com.example.designsystem.navigation.AppNavigator
import com.example.domain.usecase.GetCurrentWeatherUseCase
import com.example.domain.usecase.GetForecastWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val appNavigator: AppNavigator,
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val getForecastWeatherUseCase: GetForecastWeatherUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeScreenContract())
    val uiState = _uiState.asStateFlow()

    init {
        reducer(intent = HomeScreenIntent.UpdateCurrentWeather)
        reducer(intent = HomeScreenIntent.UpdateForecastWeather)
    }

    fun reducer(intent: HomeScreenIntent) {
        when (intent) {
            is HomeScreenIntent.UpdateCurrentWeather -> {
                _uiState.update {
                    it.copy(currentWeather = getCurrentWeatherUseCase.invoke())
                }
            }

            is HomeScreenIntent.UpdateForecastWeather -> {
                _uiState.update {
                    it.copy(forecastWeather = getForecastWeatherUseCase.invoke())
                }
            }
        }
    }

}