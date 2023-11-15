package com.example.multi_weather_app.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.FabPosition
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.data.util.NetworkStatus
import com.example.designsystem.component.WeatherDrawerMenu
import com.example.designsystem.component.WeatherTopAppBar
import com.example.designsystem.navigation.WeatherDestinations
import com.example.home.HomeScreen
import com.example.multi_weather_app.R
import com.example.multi_weather_app.MainActivityViewModel
import com.example.search.SearchScreen
import com.example.splash.SplashScreen
import kotlinx.coroutines.delay

@Composable
fun WeatherApp(
    mainActivityViewModel: MainActivityViewModel = hiltViewModel()
) {

    val networkStatus = mainActivityViewModel.networkStatus.collectAsStateWithLifecycle()
    val navController = rememberNavController()
    val snackbarHostState = SnackbarHostState()
    val notConnectedMessage = stringResource(id = R.string.not_connected)
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val startAnimation = remember { mutableStateOf(false) }
    val startMainNavigation = remember { mutableStateOf(false) }

    when (networkStatus.value) {
        NetworkStatus.Disconnected -> {
            LaunchedEffect(networkStatus) {
                snackbarHostState.showSnackbar(message = notConnectedMessage)
            }
        }

        else -> {}
    }

    LaunchedEffect(key1 = true) {
        startAnimation.value = true
        delay(2000L)
        startMainNavigation.value = true
    }
    SplashScreen()

    when (startMainNavigation.value) {
        true -> {
            Box(modifier = Modifier.fillMaxSize()) {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        WeatherTopAppBar(
                            navController = navController,
                            drawerState = drawerState
                        )
                    },
                    snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
                    floatingActionButton = { },
                    floatingActionButtonPosition = FabPosition.End
                ) { padding ->
                    Box(modifier = Modifier.padding(padding)) {
                        ModalNavigationDrawer(
                            drawerState = drawerState,
                            drawerContent = {
                                WeatherDrawerMenu(
                                    navController = navController,
                                    drawerState = drawerState,
                                    currentLocation = "Essen, Germany",
                                    favouriteLocations = listOf()
                                )
                            }
                        ) {
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
//                                composable(route = WeatherDestinations.DrawerMenuScreen.route) {
//                                    WeatherDrawerMenu(
//                                        drawerState = drawerState,
//                                        scope = scope,
//                                        navController = navController
//                                    )
//                                }
                            }
                        }
                    }
                }
            }
        }

        else -> {}
    }
}