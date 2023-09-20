package com.example.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.designsystem.component.WeatherBackground

@Composable
fun HomeScreen(navController: NavController) {

    WeatherBackground(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            HomeScreen_WeatherCard(navController = navController)
            Spacer(modifier = Modifier.height(10.dp))
            HomeScreen_NewsCard(navController = navController)
            Spacer(modifier = Modifier.height(10.dp))
            HomeScreen_ForecastCard(navController = navController)
            Spacer(modifier = Modifier.height(10.dp))
            HomeScreen_WindCard(navController = navController)
            Spacer(modifier = Modifier.height(10.dp))
            HomeScreen_CalendarCard(navController = navController)
            Spacer(modifier = Modifier.height(10.dp))
        }
    }

}

@Composable
private fun HomeScreen_WeatherCard(navController: NavController) {
    ElevatedCard(modifier = Modifier
        .fillMaxWidth()
        .padding(15.dp),
        shape = CardDefaults.elevatedShape
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
        ) {

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

