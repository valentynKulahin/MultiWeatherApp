package com.example.multi_weather_app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apps.multi_weather_app.BuildConfig
import com.example.common.network.status.NetworkStatus
import com.example.datastore.repo.DataStoreRepo
import com.example.domain.usecase.GetNetworkStatusUseCase
import com.example.navi.repo.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    appNavigator: AppNavigator,
    getNetworkStatusUseCase: GetNetworkStatusUseCase,
    private val dataStoreRepo: DataStoreRepo
) : ViewModel() {

    val navigationChannel = appNavigator.navigationChannel

    val networkStatus: StateFlow<NetworkStatus> =
        getNetworkStatusUseCase.invoke().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = NetworkStatus.Unknown
        )

    init {
        viewModelScope.launch(context = Dispatchers.IO) {
            dataStoreRepo.updateNewsToken(BuildConfig.NEWS_API_KEY)
            dataStoreRepo.updateWeatherToken(BuildConfig.WEATHER_API_KEY)
            dataStoreRepo.updateMapsToken(BuildConfig.MAPS_API_KEY)
        }
    }

}