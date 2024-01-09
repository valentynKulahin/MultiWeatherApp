package com.example.home.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.dp
import com.example.domain.model.weather.AstroDomainModel

@Composable
fun HomeScreen_Canvas(
    astroDomainModel: AstroDomainModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            val path = Path().apply {
                drawCircle(
                    color = Color.Black,
                    radius = 70F,
                    center = Offset(x = (size.minDimension + size.maxDimension) / 2, y = -150F),
                    colorFilter = ColorFilter.tint(color = Color.Yellow)
                )
                close()
            }
            drawLine(
                start = Offset(x = size.minDimension, y = 0F),
                end = Offset(x = size.maxDimension, y = 0F),
                color = Color.Black,
                strokeWidth = 10F,
            )
            drawCircle(
                color = Color.Black,
                radius = 4.dp.toPx(),
                center = Offset(x = (size.minDimension + size.maxDimension) / 2, y = 0F)
            )
            drawPath(path = path, color = Color.Yellow)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = astroDomainModel.sunrise.toString())
            Text(text = astroDomainModel.sunset.toString())
        }
    }
}