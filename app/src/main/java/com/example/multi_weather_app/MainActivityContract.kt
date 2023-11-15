package com.example.multi_weather_app

import com.example.data.util.NetworkStatus

data class MainActivityContract(
    val networkStatus: NetworkStatus = NetworkStatus.Unknown
)