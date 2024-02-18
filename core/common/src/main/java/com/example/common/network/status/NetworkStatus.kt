package com.example.common.network.status

sealed interface NetworkStatus {

    data object Unknown : NetworkStatus
    data object Connected : NetworkStatus
    data object Disconnected : NetworkStatus

}