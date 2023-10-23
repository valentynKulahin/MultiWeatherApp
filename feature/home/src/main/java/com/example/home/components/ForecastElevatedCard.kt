package com.example.home.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.common.convert.convertStringToLink
import com.example.domain.model.weather.CurrentDomainModel
import com.example.domain.model.weather.ForecastDomainModel
import com.example.domain.model.weather.HourDomainModel
import com.example.home.R
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun ForecastElevatedCard(
    navController: NavHostController,
    currentWeather: CurrentDomainModel,
    forecastWeather: ForecastDomainModel
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
    currentWeather: CurrentDomainModel,
    forecastWeather: ForecastDomainModel
) {

    val currentDateTime = LocalDateTime.now().withMinute(0).withSecond(0)

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(horizontal = 20.dp),
        shape = CardDefaults.elevatedShape
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Forecast_Card_Top(currentWeather = currentWeather)
            Forecast_Card_Lists(
                forecastWeather = forecastWeather,
                currentDateTime = currentDateTime
            )
        }
    }

}

@Composable
private fun Forecast_Card_Top(
    currentWeather: CurrentDomainModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(
                text = currentWeather.condition?.text.toString(),
                fontSize = 24.sp
            )
//            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = getCurrentFormattedDate(),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Image(
            painter = painterResource(id = R.drawable.ic_weather_menu),
            contentDescription = null,
            modifier = Modifier.size(30.dp)
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Forecast_Card_Lists(
    forecastWeather: ForecastDomainModel,
    currentDateTime: LocalDateTime
) {
    val forecastDays = forecastWeather.forecastday ?: listOf()
    val lazyListState = rememberLazyListState()

    if ((forecastDays.size) > 0) {
        forecastDays.forEach { forecastItem ->
            LazyRow(modifier = Modifier.fillMaxWidth(), state = lazyListState) {
                if ((forecastItem.hour?.size ?: 0) > 0) {
                    items(items = forecastItem.hour ?: listOf()) {
                        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
                        val forecastDateTime = LocalDateTime.parse(it.time, formatter)
                        if (forecastDateTime.hour <= currentDateTime.hour) {
                            Forecast_Card_Item(
                                hourModel = it,
                                hour = forecastDateTime.hour,
                                currentDateTime
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Forecast_Card_Item(hourModel: HourDomainModel, hour: Int, currentDateTime: LocalDateTime) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = if (hour == currentDateTime.hour) "now" else hour.toString())
        AsyncImage(
            model = convertStringToLink(hourModel.condition?.icon.toString()),
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )
        Text(
            text = "${hourModel.temp_c} \u2103",
            fontSize = 20.sp
        )
    }
}

fun getCurrentFormattedDate(): String {
    val currentDate = LocalDate.now()

    // Format the month and day
    val month = currentDate.month.getDisplayName(TextStyle.FULL, Locale.ENGLISH)
    val day = currentDate.dayOfMonth.toString() + getDayOfMonthSuffix(currentDate.dayOfMonth)
    val year = currentDate.year

    return "$month, $day $year"
}

fun getDayOfMonthSuffix(day: Int): String {
    return when (day) {
        1, 21, 31 -> "st"
        2, 22 -> "nd"
        3, 23 -> "rd"
        else -> "th"
    }
}


