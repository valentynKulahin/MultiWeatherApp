package com.example.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
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
import coil.compose.AsyncImage
import com.example.common.func.work_with_date.CommonDate.getCurrentFormattedDate
import com.example.common.func.work_with_date.CommonDate.getDayOfWeekFromDate
import com.example.common.func.work_with_url.CommonURL.convertStringToLink
import com.example.home.R
import com.example.model.model.weather.CurrentExternalModel
import com.example.model.model.weather.ForecastExternalModel
import com.example.model.model.weather.ForecastdayExternalModel
import kotlin.math.roundToInt

@Composable
fun ForecastElevatedCardDays(
    currentWeather: CurrentExternalModel,
    forecastWeather: ForecastExternalModel
) {
    Forecast_Screen_Days(
        forecastWeather = forecastWeather,
        currentWeather = currentWeather
    )
}

@Composable
private fun Forecast_Screen_Days(
    currentWeather: CurrentExternalModel,
    forecastWeather: ForecastExternalModel
) {
    Column {
//        Forecast_Screen_Header()
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 20.dp),
            shape = CardDefaults.elevatedShape
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Forecast_Card_Top_Days(currentWeather = currentWeather)
                Forecast_Card_Lists_Days(forecastWeather = forecastWeather)
            }
        }
    }

}

@Composable
private fun Forecast_Card_Top_Days(
    currentWeather: CurrentExternalModel
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
                fontSize = 20.sp
            )
            Text(
                text = getCurrentFormattedDate(),
                fontSize = 20.sp,
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

@Composable
private fun Forecast_Card_Lists_Days(
    forecastWeather: ForecastExternalModel
) {
    val forecastDays = forecastWeather.forecastday ?: listOf()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
    ) {
        forecastDays.forEach { forecastItem ->
            Forecast_Card_Item_Days(forecastItem = forecastItem)
        }
    }
}

@Composable
private fun Forecast_Card_Item_Days(
    forecastItem: ForecastdayExternalModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Forecast_Card_item_Left(forecastItem = forecastItem)
        Forecast_Card_item_Right(forecastItem = forecastItem)
    }

}

@Composable
private fun Forecast_Card_item_Left(
    forecastItem: ForecastdayExternalModel
) {
    Row(
        modifier = Modifier.padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = convertStringToLink(forecastItem.day?.condition?.icon.toString()),
            contentDescription = null,
            modifier = Modifier.size(45.dp)
        )
        Spacer(modifier = Modifier.width(14.dp))
        Text(text = getDayOfWeekFromDate(forecastItem.date.toString()))
    }
}

@Composable
private fun Forecast_Card_item_Right(
    forecastItem: ForecastdayExternalModel
) {
    Row(
        modifier = Modifier.padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "${forecastItem.day?.maxtemp_c?.roundToInt()} \u2103 / ${forecastItem.day?.maxtemp_f?.roundToInt()} \u2109")
    }
}
