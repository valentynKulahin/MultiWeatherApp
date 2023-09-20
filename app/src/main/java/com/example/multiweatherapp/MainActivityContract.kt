package com.example.multiweatherapp

import com.example.data.util.NetworkStatus

data class MainActivityContract(
    val networkStatus: NetworkStatus = NetworkStatus.Unknown
)