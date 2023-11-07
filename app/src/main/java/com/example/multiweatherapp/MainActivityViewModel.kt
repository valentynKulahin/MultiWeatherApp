package com.example.multiweatherapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.util.NetworkMonitor
import com.example.data.util.NetworkStatus
import com.example.datastore.repo.DataStoreRepo
import com.example.designsystem.navigation.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    networkMonitor: NetworkMonitor,
    appNavigator: AppNavigator,
    private val dataStoreRepo: DataStoreRepo
) : ViewModel() {

    val networkStatus: StateFlow<NetworkStatus> =
        networkMonitor.networkStatus.stateIn(
            scope = CoroutineScope(context = Dispatchers.IO),
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = NetworkStatus.Unknown
        )

    val navigatorChannel = appNavigator.navigationChannel

    init {
        viewModelScope.launch {
            dataStoreRepo.updateNewsToken(token = BuildConfig.NEWS_API_KEY)
            dataStoreRepo.updateWeatherToken(token = BuildConfig.WEATHER_API_KEY)
        }
    }

}