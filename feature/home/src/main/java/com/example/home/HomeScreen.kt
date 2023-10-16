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
import androidx.navigation.NavHostController
import com.example.designsystem.component.WeatherBackground
import com.example.domain.model.current.CurrentWeather
import com.example.domain.model.news.NewsResult
import com.example.home.components.CalendarElevatedCard
import com.example.home.components.ForecastElevatedCard
import com.example.home.components.NewsElevatedCard
import com.example.home.components.SunConditionElevatedCard
import com.example.home.components.WeatherElevatedCard
import com.example.home.components.WindElevatedCard

@Composable
fun HomeScreen(
    navController: NavHostController,
    homeVM: HomeScreenViewModel = hiltViewModel()
) {

    val uiState = homeVM.uiState.collectAsStateWithLifecycle()
    val currentWeather = remember { mutableStateOf(uiState.value.currentWeather) }
    val news = remember { mutableStateOf(uiState.value.news) }
    val forecastWeather = remember { mutableStateOf(uiState.value.forecastWeather) }

    WeatherBackground(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            HomeScreen_WeatherCard(
                navController = navController,
                currentWeather = currentWeather.value
            )
            Spacer(modifier = Modifier.height(10.dp))
            HomeScreen_NewsCard(navController = navController, news = news.value)
            Spacer(modifier = Modifier.height(10.dp))
            HomeScreen_ForecastCard(navController = navController)
            Spacer(modifier = Modifier.height(10.dp))
            HomeScreen_SunCondition(navController = navController)
            Spacer(modifier = Modifier.height(10.dp))
            HomeScreen_WindCard(navController = navController)
            Spacer(modifier = Modifier.height(10.dp))
            HomeScreen_CalendarCard(navController = navController)
            Spacer(modifier = Modifier.height(10.dp))
        }
    }

}

@Composable
private fun HomeScreen_WeatherCard(
    navController: NavHostController,
    currentWeather: CurrentWeather
) {
    WeatherElevatedCard(
        navController = navController,
        currentWeather = currentWeather
    )
}

@Composable
private fun HomeScreen_NewsCard(navController: NavHostController, news: NewsResult) {
    NewsElevatedCard(navController = navController, news = news)
}

@Composable
private fun HomeScreen_ForecastCard(navController: NavHostController) {
    ForecastElevatedCard(navController = navController)
}

@Composable
private fun HomeScreen_SunCondition(navController: NavHostController) {
    SunConditionElevatedCard(navController = navController)
}

@Composable
private fun HomeScreen_WindCard(navController: NavHostController) {
    WindElevatedCard(navController = navController)
}

@Composable
private fun HomeScreen_CalendarCard(navController: NavHostController) {
    CalendarElevatedCard(navController = navController)
}
