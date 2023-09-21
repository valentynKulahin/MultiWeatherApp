package com.example.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.designsystem.component.WeatherBackground
import com.example.domain.model.current.CurrentWeather

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
            HomeScreen_WeatherCard(
                navController = navController,
                currentWeather = currentWeather.value
            )
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
private fun HomeScreen_WeatherCard(
    navController: NavController,
    currentWeather: CurrentWeather
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(15.dp),
        shape = CardDefaults.elevatedShape
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                HomeScreen_WeatherCard_Left(currentWeather = currentWeather)
                HomeScreen_WeatherCard_Right(currentWeather = currentWeather)
            }
            HomeScreen_WeatherCard_Bottom(currentWeather = currentWeather)
        }
    }
}

@Composable
private fun HomeScreen_WeatherCard_Left(
    currentWeather: CurrentWeather
) {
    Row {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row {
                Text(text = stringResource(id = R.string.chance_of_rain), fontSize = 16.sp)
                Spacer(modifier = Modifier.width(6.dp))
                Text(text = "10%", fontSize = 16.sp)
            }
            Text(text = currentWeather.current?.condition?.text.toString(), fontSize = 25.sp)
            Row {
                Icon(imageVector = Icons.Default.LocationOn, contentDescription = null)
                Spacer(modifier = Modifier.width(6.dp))
                Text(text = currentWeather.location?.country.toString())
                Text(text = ",")
                Text(text = currentWeather.location?.name.toString())
            }
        }
    }
}

@Composable
private fun HomeScreen_WeatherCard_Right(
    currentWeather: CurrentWeather
) {
    val iconURL = currentWeather.current?.condition?.icon.toString().replace("//", "")
    AsyncImage(model = iconURL, contentDescription = null, modifier = Modifier.size(150.dp))
}

@Composable
private fun HomeScreen_WeatherCard_Bottom(currentWeather: CurrentWeather) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        val iconURL = currentWeather.current?.condition?.icon.toString().replace("//", "")

        Text(text = (currentWeather.current?.temp_c.toString() + " \u2103"))
        AsyncImage(model = iconURL, contentDescription = null, modifier = Modifier.size(50.dp))
        Text(text = "10%")
        Icon(
            imageVector = Icons.Default.WbSunny,
            contentDescription = null,
            modifier = Modifier.size(50.dp)
        )
        Text(text = "10%")
        Icon(
            painter = painterResource(id = R.drawable.ic_weather_wind),
            contentDescription = null,
            modifier = Modifier.size(50.dp)
        )
        Text(text = currentWeather.current?.wind_mph.toString())
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
