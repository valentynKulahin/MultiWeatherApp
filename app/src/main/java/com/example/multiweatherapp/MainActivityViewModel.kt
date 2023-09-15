package com.example.multiweatherapp

import androidx.lifecycle.ViewModel
import com.example.data.util.NetworkMonitor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val networkMonitor: NetworkMonitor
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainActivityContract())
    val uiState = _uiState.asStateFlow()

    init {
        reducer(MainActivityState.CheckConnection)
    }

    fun reducer(intent: MainActivityState) {
        when (intent) {
            is MainActivityState.CheckConnection -> {
                _uiState.update {
                    it.copy(isConnected = networkMonitor.isOnline)
                }
            }
        }
    }

}