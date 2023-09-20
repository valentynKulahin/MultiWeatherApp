package com.example.designsystem.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class WeatherDestinations(val route: String, val icon: ImageVector?, label: String? = null) {

    data object HomeScreen :
        WeatherDestinations(route = "home", icon = Icons.Default.Home, label = "Home")

    data object DetailScreen :
        WeatherDestinations(route = "detail", icon = null, label = "Details")

    data object LocationScreen :
        WeatherDestinations(route = "location", icon = null, label = "Location")

    data object NewsScreen :
        WeatherDestinations(route = "news", icon = null, label = "News")

    data object SettingsScreen :
        WeatherDestinations(route = "settings", icon = null, label = "Settings")

    data object SearchScreen :
        WeatherDestinations(route = "search", icon = Icons.Default.Search, label = "Search")

    data object DrawerMenuScreen :
        WeatherDestinations(route = "drawer", icon = Icons.Default.Menu, label = "Menu")
}