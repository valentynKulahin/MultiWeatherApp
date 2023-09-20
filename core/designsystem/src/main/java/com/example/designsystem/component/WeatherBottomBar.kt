package com.example.designsystem.component

import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NotInterested
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.designsystem.navigation.WeatherDestinations

@Composable
fun WeatherBottomBar(
    navController: NavHostController
) {
    val items = listOf(
        WeatherDestinations.HomeScreen,
        WeatherDestinations.SearchScreen
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    NavigationBar {
        items.forEach { item ->
            val selected = item.route == navBackStackEntry?.destination?.route

            NavigationBarItem(
                selected = selected,
                onClick = {
                    navController.navigate(route = item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                label = { Text(text = item.route) },
                icon = {
                    Icon(
                        imageVector = item.icon ?: Icons.Default.NotInterested,
                        contentDescription = null
                    )
                }
            )
        }
    }
}