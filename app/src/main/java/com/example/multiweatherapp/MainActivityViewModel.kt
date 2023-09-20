package com.example.multiweatherapp

import androidx.lifecycle.ViewModel
import com.example.data.util.NetworkMonitor
import com.example.data.util.NetworkStatus
import com.example.designsystem.navigation.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val networkMonitor: NetworkMonitor,
    private val appNavigator: AppNavigator
) : ViewModel() {

    val networkStatus: StateFlow<NetworkStatus> =
        networkMonitor.networkStatus.stateIn(
            scope = CoroutineScope(context = Dispatchers.IO),
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = NetworkStatus.Unknown
        )

    val navigatorChannel = appNavigator.navigationChannel

}