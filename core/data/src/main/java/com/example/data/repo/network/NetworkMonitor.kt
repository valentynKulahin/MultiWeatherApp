package com.example.data.repo.network

import com.example.common.network.status.NetworkStatus
import kotlinx.coroutines.flow.Flow

interface NetworkMonitor {
    val networkStatus: Flow<NetworkStatus>
}