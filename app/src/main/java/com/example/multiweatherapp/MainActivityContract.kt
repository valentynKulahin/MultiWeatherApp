package com.example.multiweatherapp

import kotlinx.coroutines.flow.Flow

data class MainActivityContract(
    val isConnected: Flow<Boolean>? = null
)

sealed interface MainActivityState {
    data object CheckConnection : MainActivityState
}