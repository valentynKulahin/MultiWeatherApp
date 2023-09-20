package com.example.designsystem.navigation

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import javax.inject.Inject

class AppNavigatorImpl @Inject constructor() : AppNavigator {
    override val navigationChannel: Channel<NavigationIntent>
        get() = Channel(
            capacity = Int.MAX_VALUE,
            onBufferOverflow = BufferOverflow.DROP_LATEST
        )

    override suspend fun navigationBack(route: String?) {
        navigationChannel.send(NavigationIntent.NavigationBack(route = route))
    }

    override fun tryNavigationBack(route: String?) {
        navigationChannel.trySend(NavigationIntent.NavigationBack(route = route))
    }

    override suspend fun navigationTo(route: String?, popUpToRoute: String?) {
        navigationChannel.send(
            NavigationIntent.NavigationTo(
                route = route,
                popUpToRoute = popUpToRoute
            )
        )
    }

    override fun tryNavigationTo(route: String?, popUpToRoute: String?) {
        navigationChannel.trySend(
            NavigationIntent.NavigationTo(
                route = route,
                popUpToRoute = popUpToRoute
            )
        )
    }

}