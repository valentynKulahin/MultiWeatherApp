package com.example.home.components

import android.graphics.Bitmap
import android.graphics.BitmapFactory
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.designsystem.R
import com.example.model.model.weather.AstroExternalModel
import com.example.model.model.weather.CurrentExternalModel

@Composable
fun SunConditionElevatedCard(
    currentExternalModel: CurrentExternalModel,
    astroExternalModel: AstroExternalModel
) {

    SunCondition_Screen(
        currentExternalModel = currentExternalModel,
        astroExternalModel = astroExternalModel
    )

}

@Composable
private fun SunCondition_Screen(
    currentExternalModel: CurrentExternalModel,
    astroExternalModel: AstroExternalModel
) {
    Column {
        SunCondition_Header()
        SunCondition_Screen_Card(
            currentExternalModel = currentExternalModel,
            astroExternalModel = astroExternalModel
        )
    }
}

@Composable
private fun SunCondition_Screen_Card(
    currentExternalModel: CurrentExternalModel,
    astroExternalModel: AstroExternalModel
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
            SunCondition_Top(currentExternalModel = currentExternalModel)
            SunCondition_Bottom(
                astroExternalModel = astroExternalModel,
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
private fun SunCondition_Top(currentExternalModel: CurrentExternalModel) {
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
            Text(text = currentExternalModel.uv.toString(), fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun SunCondition_Bottom(
    astroExternalModel: AstroExternalModel,
//    scope: CoroutineScope
) {

    val bitmap = BitmapFactory.decodeResource(LocalContext.current.resources, com.example.home.R.drawable.sun)
    val bitmapImage = Bitmap.createBitmap(bitmap, 0, 0, 40, 40).asImageBitmap()

    Column(modifier = Modifier.fillMaxWidth()) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            drawImage(
                image = bitmapImage,
                topLeft = Offset(
                    x = (size.minDimension + size.maxDimension) / 2,
                    y = -40.dp.toPx()
                ),
                colorFilter = ColorFilter.tint(color = Color.Black)
            )
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

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = astroExternalModel.sunrise.toString())
            Text(text = astroExternalModel.sunset.toString())
        }
    }
}



