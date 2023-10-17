package com.example.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.domain.model.current.CurrentWeather
import com.example.domain.model.forecast.ForecastWeather
import com.example.home.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun ForecastElevatedCard(
    navController: NavHostController,
    currentWeather: CurrentWeather,
    forecastWeather: ForecastWeather
) {
    Forecast_Screen(
        navController = navController,
        forecastWeather = forecastWeather,
        currentWeather = currentWeather
    )
}

@Composable
private fun Forecast_Screen(
    navController: NavHostController,
    currentWeather: CurrentWeather,
    forecastWeather: ForecastWeather
) {

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(20.dp),
        shape = CardDefaults.elevatedShape
    ) {
        Column {
            Forecast_Card_Top(currentWeather = currentWeather)
            Forecast_Card_Lists()
        }
    }

}

@Composable
private fun Forecast_Card_Top(
    currentWeather: CurrentWeather
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(
                text = currentWeather.current?.condition?.text.toString(),
                fontSize = 24.sp
            )
            Text(
                text = getCurrentFormattedDate(),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Image(painter = painterResource(id = R.drawable.ic_weather_menu), contentDescription = null)
    }
}

@Composable
private fun Forecast_Card_Lists() {

}

fun getCurrentFormattedDate(): String {
    val currentDate = LocalDate.now()

    // Format the date using a custom pattern
    val formatter =
        DateTimeFormatter.ofPattern("MMMM, d${getDayOfMonthSuffix(currentDate.dayOfMonth)} yyyy")
    return currentDate.format(formatter)
}

fun getDayOfMonthSuffix(day: Int): String {
    return when (day) {
        1, 21, 31 -> "st"
        2, 22 -> "nd"
        3, 23 -> "rd"
        else -> "th"
    }
}
