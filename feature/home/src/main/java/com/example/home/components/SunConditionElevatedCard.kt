package com.example.home.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.designsystem.R
import com.example.domain.model.weather.AstroDomainModel
import com.example.domain.model.weather.CurrentDomainModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun SunConditionElevatedCard(
    currentDomainModel: CurrentDomainModel,
    astroDomainModel: AstroDomainModel,
    scope: CoroutineScope = rememberCoroutineScope()
) {

    SunCondition_Screen(
        currentDomainModel = currentDomainModel,
        astroDomainModel = astroDomainModel,
        scope = scope
    )

}

@Composable
private fun SunCondition_Screen(
    currentDomainModel: CurrentDomainModel,
    astroDomainModel: AstroDomainModel,
    scope: CoroutineScope
) {
    Column {
        SunCondition_Header()
        SunCondition_Screen_Card(
            currentDomainModel = currentDomainModel,
            astroDomainModel = astroDomainModel,
            scope = scope
        )
    }
}

@Composable
private fun SunCondition_Screen_Card(
    currentDomainModel: CurrentDomainModel,
    astroDomainModel: AstroDomainModel,
    scope: CoroutineScope
) {

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(20.dp),
        shape = CardDefaults.elevatedShape
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            SunCondition_Top(currentDomainModel = currentDomainModel)
            SunCondition_Bottom(
                astroDomainModel = astroDomainModel,
//                scope = scope
            )
        }
    }

}

@Composable
private fun SunCondition_Header() {
    Text(
        modifier = Modifier.padding(horizontal = 10.dp),
        text = stringResource(id = R.string.sun_condition_header)
    )
}

@Composable
private fun SunCondition_Top(currentDomainModel: CurrentDomainModel) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(id = R.string.sun_conditional_text))
            Text(text = stringResource(id = R.string.uv_index_text))
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(id = R.string.sun_text))
            Text(text = currentDomainModel.uv.toString(), fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun SunCondition_Bottom(
    astroDomainModel: AstroDomainModel,
//    scope: CoroutineScope
) {
//    val vector = Icons.Default.WbSunny
//    val painter = rememberVectorPainter(image = vector)

//    val imageSunny =
//        remember { mutableStateOf(ImageBitmap.imageResource(com.example.home.R.drawable.ic_weather_sunny)) }


    Column(modifier = Modifier.fillMaxWidth()) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
//            with(painter) {
//                draw(size = painter.intrinsicSize.copy(width = 40.dp.toPx(), height = 40.dp.toPx()))
//            }
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
//            drawImage(
//                image = Icons.Default.WbSunny,
//                topLeft = Offset((size.minDimension + size.maxDimension) / 2, y = 0F),
//                colorFilter = ColorFilter.tint(color = Color.Black)
//            )
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



