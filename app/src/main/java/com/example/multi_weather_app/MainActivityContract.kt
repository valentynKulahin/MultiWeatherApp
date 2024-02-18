package com.example.multi_weather_app

sealed interface MainActivityUiState {

    data object Loading : MainActivityUiState

    data object Success : MainActivityUiState

}