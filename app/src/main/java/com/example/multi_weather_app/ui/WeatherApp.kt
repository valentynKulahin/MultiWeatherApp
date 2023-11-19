package com.example.multi_weather_app.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector
import androidx.compose.animation.core.calculateTargetValue
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.FabPosition
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
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
import com.example.home.HomeScreen
import com.example.multi_weather_app.R
import com.example.multi_weather_app.MainActivityViewModel
import com.example.search.SearchScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun WeatherApp(
    mainActivityViewModel: MainActivityViewModel = hiltViewModel(),
    scope: CoroutineScope = rememberCoroutineScope()
) {

    val networkStatus = mainActivityViewModel.networkStatus.collectAsStateWithLifecycle()
    val navController = rememberNavController()
    val snackbarHostState = SnackbarHostState()
    val notConnectedMessage = stringResource(id = R.string.not_connected)
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val translationX = remember { Animatable(0F) }
    val draggableState = rememberDraggableState(
        onDelta = { dragAmount ->
            scope.launch { translationX.snapTo(translationX.value + dragAmount) }
        }
    )
    val drawerWidth = 300.dp
    val decay = rememberSplineBasedDecay<Float>()
    translationX.updateBounds(0f, drawerWidth.value)

    when (networkStatus.value) {
        NetworkStatus.Disconnected -> {
            LaunchedEffect(networkStatus) {
                snackbarHostState.showSnackbar(message = notConnectedMessage)
            }
        }

        else -> {}
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        ModalNavigationDrawer(
            modifier = Modifier,
            drawerState = drawerState,
            drawerContent = {
                WeatherDrawerMenu(
                    navController = navController,
                    drawerState = drawerState,
                    currentLocation = "Essen, Germany",
                    favouriteLocations = listOf(),
                    drawerWidth = drawerWidth
                )
            }
        ) {
            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        this.translationX = translationX.value
//                        val scale =
//                            lerp(1F.toDp(), 1F.toDp(), translationX.value / drawerWidth.value)
//                        this.scaleX = scale.value
//                        this.scaleY = scale.value
                    }
                    .draggable(
                        state = draggableState,
                        orientation = Orientation.Horizontal,
                        onDragStopped = { velocity ->
                            val decayX = decay.calculateTargetValue(
                                initialValue = translationX.value,
                                initialVelocity = velocity
                            )
                            scope.launch {
                                val targetX = if (decayX > drawerWidth.value * 0.5) {
                                    drawerWidth
                                } else {
                                    0.dp
                                }
                                val canReachTagretWithDecay =
                                    (decayX.dp > targetX && targetX == drawerWidth)
                                            || (decayX.dp < targetX && targetX == 0.dp)
                                if (canReachTagretWithDecay) {
                                    translationX.animateDecay(
                                        initialVelocity = velocity,
                                        animationSpec = decay
                                    )
                                } else {
                                    translationX.animateTo(
                                        targetValue = targetX.value,
                                        initialVelocity = velocity
                                    )
                                }
                                if (targetX == drawerWidth) {
                                    drawerState.open()
                                } else {
                                    drawerState.close()
                                }
                            }
                        }
                    ),
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
                    NavHost(
                        navController = navController,
                        startDestination = WeatherDestinations.HomeScreen.route
                    ) {
                        composable(route = WeatherDestinations.HomeScreen.route) {
                            HomeScreen(
                                drawerState = drawerState,
                                navController = navController
                            )
                        }
                        composable(route = WeatherDestinations.SearchScreen.route) {
                            SearchScreen()
                        }
                    }
                }
            }
        }
    }
}

fun droggleDrawerState(
    scope: CoroutineScope,
    drawerState: DrawerState,
    translationX: Animatable<Float, AnimationVector>,
    drawerWidth: Dp
) {
    scope.launch {
        if (drawerState.isOpen) {
            translationX.animateTo(0F)
        } else {
            translationX.animateTo(drawerWidth.value)
        }
        if (drawerState.isOpen) {
            drawerState.close()
        } else {
            drawerState.open()
        }
    }
}

