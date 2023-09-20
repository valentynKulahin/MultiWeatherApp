package com.example.data.util

sealed interface NetworkStatus {

    data object Unknown : NetworkStatus
    data object Connected : NetworkStatus
    data object Disconnected : NetworkStatus

}