package com.example.multiweatherapp.ui

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.util.NetworkMonitor
import com.example.multiweatherapp.MainActivityViewModel

@Composable
fun WeatherApp(
    mainActivityViewModel: MainActivityViewModel
) {

    val state = mainActivityViewModel.uiState.collectAsStateWithLifecycle()
    val isOnline = remember { mutableStateOf(state.value.isConnected) }
    Log.d("TAG", "WeatherApp: ${isOnline.value}")

}