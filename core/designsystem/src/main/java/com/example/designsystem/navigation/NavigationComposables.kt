package com.example.designsystem.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

//@Composable
//fun NavHost(
//    navController: NavHostController,
//    startDestinations: WeatherDestinations,
//    modifier: Modifier = Modifier,
//    route: String? = null,
//    builder: NavGraphBuilder.() -> Unit = {}
//) {
//    NavHost(
//        navController = navController,
//        startDestinations = startDestinations,
//        modifier = modifier,
//        route = route,
//        builder = builder
//    )
//}
//
//fun NavGraphBuilder.composable(
//    destinations: WeatherDestinations,
//    content: @Composable () -> Unit = {}
//) {
//    composable(route = destinations.route) {
//        content()
//    }
//}