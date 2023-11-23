package com.example.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.designsystem.component.WeatherBackground
import com.example.domain.model.news.NewsDomainModel
import com.example.domain.model.weather.CurrentDomainModel
import com.example.domain.model.weather.ForecastDomainModel
import com.example.domain.model.weather.LocationDomainModel
import com.example.domain.model.weather.WeatherDomainModel
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
    val verticalScrollState = rememberScrollState()

    WeatherBackground(modifier = Modifier.wrapContentHeight()) {
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .verticalScroll(
                    state = verticalScrollState,
                    enabled = true
                )
        ) {
            HomeScreen_WeatherCard(
                navController = navController,
                weatherDomainModel = uiState.value.weatherDomainModel
            )
            Spacer(modifier = Modifier.height(10.dp))
            HomeScreen_NewsCard(news = uiState.value.newsDomainModel)
            Spacer(modifier = Modifier.height(10.dp))
            HomeScreen_ForecastCard(
                currentWeather = uiState.value.weatherDomainModel.current ?: CurrentDomainModel(),
                forecastWeather = uiState.value.weatherDomainModel.forecast ?: ForecastDomainModel()
            )
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
    weatherDomainModel: WeatherDomainModel
) {
    WeatherElevatedCard(
        navController = navController,
        currentWeather = weatherDomainModel.current ?: CurrentDomainModel(),
        locationDomainModel = weatherDomainModel.location ?: LocationDomainModel()
    )
}

@Composable
private fun HomeScreen_NewsCard(news: NewsDomainModel) {
    NewsElevatedCard(news = news)
}

@Composable
private fun HomeScreen_ForecastCard(
    currentWeather: CurrentDomainModel,
    forecastWeather: ForecastDomainModel
) {
    ForecastElevatedCard(
        currentWeather = currentWeather,
        forecastWeather = forecastWeather
    )
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
