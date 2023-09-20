package com.example.multiweatherapp.ui

import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.data.util.NetworkStatus
import com.example.designsystem.component.WeatherBottomBar
import com.example.designsystem.component.WeatherDrawerMenu
import com.example.designsystem.component.WeatherTopAppBar
import com.example.designsystem.navigation.NavigationIntent
import com.example.designsystem.navigation.WeatherDestinations
import com.example.home.HomeScreen
import com.example.multiweatherapp.MainActivityViewModel
import com.example.multiweatherapp.R
import com.example.search.SearchScreen
import com.example.splash.SplashScreen
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow

@Composable
fun WeatherApp(
    mainActivityViewModel: MainActivityViewModel = hiltViewModel()
) {

    val networkStatus = mainActivityViewModel.networkStatus.collectAsStateWithLifecycle()
    val navController = rememberNavController()
    val snackbarHostState = SnackbarHostState()
    val notConnectedMessage = stringResource(id = R.string.not_connected)
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    when (networkStatus.value) {
        NetworkStatus.Disconnected -> {
            LaunchedEffect(networkStatus) {
                snackbarHostState.showSnackbar(message = notConnectedMessage)
            }
        }

        else -> {}
    }

    val startAnimation = remember { mutableStateOf(false) }
    val startMainNavigation = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = true) {
        startAnimation.value = true
        delay(2000L)
        startMainNavigation.value = true
    }
    SplashScreen()

    when (startMainNavigation.value) {
        true ->
            CompositionLocalProvider {
                NavigationEffects(
                    navigationChannel = mainActivityViewModel.navigatorChannel,
                    navHostController = navController
                )
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        WeatherTopAppBar(
                            navController = navController,
                            drawerState = drawerState,
                            scope = scope
                        )
                    },
                    bottomBar = {
                        WeatherBottomBar(navController = navController)
                    },
                    snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
                    floatingActionButton = { },
                    floatingActionButtonPosition = FabPosition.End
                ) { padding ->
                    Box(modifier = Modifier.padding(padding)) {
                        NavHost(
                            navController = navController,
                            startDestination = WeatherDestinations.HomeScreen.route
                        ) {
                            composable(route = WeatherDestinations.HomeScreen.route) {
                                HomeScreen(navController = navController)
                            }
                            composable(route = WeatherDestinations.SearchScreen.route) {
                                SearchScreen()
                            }
                            composable(route = WeatherDestinations.DrawerMenuScreen.route) {
                                WeatherDrawerMenu(
                                    drawerState = drawerState,
                                    scope = scope,
                                    navController = navController
                                )
                            }
                        }
                    }
                }
            }

        else -> {}
    }

}

@Composable
fun NavigationEffects(
    navigationChannel: Channel<NavigationIntent>,
    navHostController: NavHostController
) {
    val activity = (LocalContext.current as? Activity)
    LaunchedEffect(activity, navHostController, navigationChannel) {
        navigationChannel.receiveAsFlow().collect { intent ->
            if (activity?.isFinishing == true) {
                return@collect
            }
            when (intent) {
                is NavigationIntent.NavigationBack -> {
                    navHostController.popBackStack()
                }

                is NavigationIntent.NavigationTo -> {
                    navHostController.navigate(route = intent.route ?: "") {
                        launchSingleTop = true
                        intent.popUpToRoute?.let { popUpToRoute ->
                            popUpTo(popUpToRoute)
                        }
                    }
                }
            }
        }
    }
}