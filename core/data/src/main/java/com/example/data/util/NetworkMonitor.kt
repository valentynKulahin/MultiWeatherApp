package com.example.data.util

import kotlinx.coroutines.flow.Flow

interface NetworkMonitor {
    val networkStatus: Flow<NetworkStatus>
}