package com.example.designsystem.navigation

import kotlinx.coroutines.channels.Channel

interface AppNavigator {
    val navigationChannel: Channel<NavigationIntent>

    suspend fun navigationBack(route: String? = null)

    fun tryNavigationBack(route: String? = null)

    suspend fun navigationTo(
        route: String? = null,
        popUpToRoute: String? = null
    )

    fun tryNavigationTo(
        route: String? = null,
        popUpToRoute: String? = null
    )
}

sealed class NavigationIntent {
    data class NavigationBack(
        val route: String? = null
    ) : NavigationIntent()

    data class NavigationTo(
        val route: String? = null,
        val popUpToRoute: String? = null
    ) : NavigationIntent()
}