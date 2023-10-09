package com.example.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.designsystem.component.WeatherBackground
import com.example.home.components.WeatherElevatedCard

@Composable
fun HomeScreen(
    navController: NavController,
    homeVM: HomeScreenViewModel = hiltViewModel()
) {

    val uiState = homeVM.uiState.collectAsStateWithLifecycle()
    val currentWeather = remember { mutableStateOf(uiState.value.currentWeather) }
    val forecastWeather = remember { mutableStateOf(uiState.value.forecastWeather) }

    WeatherBackground(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            WeatherElevatedCard(
                navController = navController,
                currentWeather = currentWeather.value
            )
            Spacer(modifier = Modifier.height(10.dp))
//            HomeScreen_NewsCard(navController = navController)
//            Spacer(modifier = Modifier.height(10.dp))
//            HomeScreen_ForecastCard(navController = navController)
//            Spacer(modifier = Modifier.height(10.dp))
//            HomeScreen_WindCard(navController = navController)
//            Spacer(modifier = Modifier.height(10.dp))
//            HomeScreen_CalendarCard(navController = navController)
//            Spacer(modifier = Modifier.height(10.dp))
        }
    }

}

@Composable
private fun HomeScreen_NewsCard(navController: NavController) {

}

@Composable
private fun HomeScreen_ForecastCard(navController: NavController) {

}

@Composable
private fun HomeScreen_WindCard(navController: NavController) {

}

@Composable
private fun HomeScreen_CalendarCard(navController: NavController) {

}
