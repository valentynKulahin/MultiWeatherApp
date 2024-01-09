package com.example.multi_weather_app.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.FabPosition
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.data.util.NetworkStatus
import com.example.designsystem.component.WeatherDrawerMenu
import com.example.designsystem.component.WeatherTopAppBar
import com.example.designsystem.navigation.WeatherDestinations
import com.example.designsystem.permissions.RequestPermissions
import com.example.home.HomeScreen
import com.example.multi_weather_app.R
import com.example.multi_weather_app.MainActivityViewModel
import com.example.search.SearchScreen
import kotlin.math.roundToInt

enum class DragAnchors {
    Start,
    End,
}

@RequiresApi(Build.VERSION_CODES.S)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WeatherApp(
    mainActivityViewModel: MainActivityViewModel = hiltViewModel()
) {

    val networkStatus = mainActivityViewModel.networkStatus.collectAsStateWithLifecycle()
    val navController = rememberNavController()
    val snackbarState = SnackbarHostState()
    val snackbarText = remember { mutableStateOf("") }
    val notConnectedMessage = stringResource(id = R.string.not_connected)
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val drawerWidth = 0.8F
    val draggableAnchors = DraggableAnchors {
        DragAnchors.Start at 0F
        DragAnchors.End at drawerWidth
    }
    val dencity = LocalDensity.current
    val draggableState = remember {
        AnchoredDraggableState(
            initialValue = DragAnchors.Start,
            anchors = draggableAnchors,
            positionalThreshold = { distance: Float -> distance * 0.5F },
            animationSpec = spring(),
            velocityThreshold = {
                with(dencity) { 100.dp.toPx() }
            }
        ).apply {
            updateAnchors(
                DraggableAnchors {
                    DragAnchors.Start at 0F
                    DragAnchors.End at 0.8F
                }
            )
        }
    }

    when (networkStatus.value) {
        NetworkStatus.Disconnected -> {
            LaunchedEffect(networkStatus) {
                snackbarState.showSnackbar(message = notConnectedMessage)
            }
        }

        else -> {}
    }

    RequestPermissions()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        ModalNavigationDrawer(
            modifier = Modifier.offset {
                IntOffset(
                    x = draggableState
                        .requireOffset()
                        .roundToInt(), y = 0
                )
            },
            drawerState = drawerState,
            drawerContent = {
                WeatherDrawerMenu(
                    modifier = Modifier,
                    navController = navController,
                    drawerState = drawerState,
                    currentLocation = "Essen, Germany",
                    favouriteLocations = listOf(),
                    drawerWidth = drawerWidth
                )
            }
        ) {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = {
                    WeatherTopAppBar(
                        navController = navController,
                        screenName = "Home",
                        drawerState = drawerState,
                        onClickSearchAction = { navController.navigate(route = WeatherDestinations.SearchScreen.route) }
                    )
                },
                snackbarHost = {
                    SnackbarHost(
                        hostState = snackbarState,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                    ) {
                        Snackbar {
                            Text(text = snackbarText.value)
                        }
                    }
                },
                floatingActionButton = { },
                floatingActionButtonPosition = FabPosition.End
            ) { padding ->
                Box(
                    modifier = Modifier.padding(padding)
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = WeatherDestinations.HomeScreen.route
                    ) {
                        composable(route = WeatherDestinations.HomeScreen.route) {
                            HomeScreen(
                                navController = navController
                            )
                        }
                        composable(route = WeatherDestinations.SearchScreen.route) {
                            SearchScreen(
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}

