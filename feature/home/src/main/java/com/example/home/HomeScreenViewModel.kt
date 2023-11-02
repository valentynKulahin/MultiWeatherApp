package com.example.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetWeatherUseCase
import com.example.domain.usecase.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val getNewsUseCase: GetNewsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeScreenContract())
    val uiState = _uiState.asStateFlow()

    init {
        reducer(intent = HomeScreenIntent.UpdateWeather)
        reducer(intent = HomeScreenIntent.UpdateNews)
    }

    fun reducer(intent: HomeScreenIntent) {
        when (intent) {
            is HomeScreenIntent.UpdateWeather -> {
                viewModelScope.launch(context = Dispatchers.IO) {
                    _uiState.update {
                        it.copy(weather = getWeatherUseCase.invoke())
                    }
                }
            }

            is HomeScreenIntent.UpdateNews -> {
                viewModelScope.launch(context = Dispatchers.IO) {
                    _uiState.update {
                        it.copy(news = getNewsUseCase.invoke())
                    }
                }
            }
        }
    }

}