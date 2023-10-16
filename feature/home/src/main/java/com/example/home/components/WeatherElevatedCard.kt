package com.example.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.designsystem.component.WeatherLocationText
import com.example.domain.model.current.CurrentWeather
import com.example.home.R

@Composable
fun WeatherElevatedCard(navController: NavHostController, currentWeather: CurrentWeather) {
    HomeScreen_WeatherCard(navController = navController, currentWeather = currentWeather)
}

@Composable
private fun HomeScreen_WeatherCard(
    navController: NavHostController,
    currentWeather: CurrentWeather
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(20.dp),
        shape = CardDefaults.elevatedShape
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                HomeScreen_WeatherCard_Left(currentWeather = currentWeather)
                HomeScreen_WeatherCard_Right(currentWeather = currentWeather)
            }
            Spacer(modifier = Modifier.height(15.dp))
            HomeScreen_WeatherCard_Bottom(currentWeather = currentWeather)
        }
    }
}

@Composable
private fun HomeScreen_WeatherCard_Left(
    currentWeather: CurrentWeather
) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Row {
            Text(text = stringResource(id = R.string.chance_of_rain), fontSize = 16.sp)
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = "10%",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = currentWeather.current?.condition?.text.toString(),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(19.dp))
        Row {
            WeatherLocationText(
                imageVector = Icons.Default.LocationOn,
                text = "${currentWeather.location?.country.toString()}, ${currentWeather.location?.name.toString()}",
                spacer = 6
            )
        }
    }
}

@Composable
private fun HomeScreen_WeatherCard_Right(
    currentWeather: CurrentWeather
) {
    val iconURL = "https:" + currentWeather.current?.condition?.icon.toString().replace("//", "")
    AsyncImage(
        model = iconURL,
        contentDescription = "weather",
        modifier = Modifier.size(110.dp)
    )
}

@Composable
private fun HomeScreen_WeatherCard_Bottom(currentWeather: CurrentWeather) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        HomeScreen_WeatherCard_Bottom_Degrees(temperature = currentWeather.current?.temp_c.toString())
        HomeScreen_WeatherCard_Bottom_ChanceOfRain(chanceOfRain = "10%")
        HomeScreen_WeatherCard_Bottom_Sunny(sunny = "10%")
        HomeScreen_WeatherCard_Bottom_Wind(wind = currentWeather.current?.wind_mph.toString())
    }
}

@Composable
private fun HomeScreen_WeatherCard_Bottom_Degrees(temperature: String) {
    Text(
        text = "$temperature \u2103",
        style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
    )
}

@Composable
private fun HomeScreen_WeatherCard_Bottom_ChanceOfRain(chanceOfRain: String) {
    Row {
        Icon(
            painter = painterResource(id = R.drawable.ic_weather_rain),
            contentDescription = null,
            modifier = Modifier.size(20.dp)
        )
        Text(
            text = chanceOfRain,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun HomeScreen_WeatherCard_Bottom_Sunny(sunny: String) {
    Row {
        Icon(
            painter = painterResource(id = R.drawable.ic_weather_sunny),
            contentDescription = null,
            modifier = Modifier.size(20.dp)
        )
        Text(
            text = sunny,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun HomeScreen_WeatherCard_Bottom_Wind(wind: String) {
    Row {
        Icon(
            painter = painterResource(id = R.drawable.ic_weather_wind),
            contentDescription = null,
            modifier = Modifier.size(20.dp)
        )
        Text(text = wind, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
    }
}