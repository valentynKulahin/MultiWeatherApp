package com.example.data.util

import com.example.common.network.status.NetworkStatus
import kotlinx.coroutines.flow.Flow

interface NetworkMonitor {
    val networkStatus: Flow<NetworkStatus>
}