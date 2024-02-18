package com.example.domain.usecase

import com.example.data.util.NetworkMonitor
import com.example.common.network.status.NetworkStatus
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetNetworkStatusUseCase @Inject constructor(
    private val networkMonitor: NetworkMonitor
) {

    operator fun invoke(): Flow<NetworkStatus> {
        return networkMonitor.networkStatus
    }

}