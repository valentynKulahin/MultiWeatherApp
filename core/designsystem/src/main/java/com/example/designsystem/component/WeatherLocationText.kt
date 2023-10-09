package com.example.designsystem.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun WeatherLocationText(
    imageVector: ImageVector,
    text: String,
    spacer: Int,
    style: TextStyle = LocalTextStyle.current
) {
    Row {
        Icon(imageVector = imageVector, contentDescription = null)
        Spacer(modifier = Modifier.width(spacer.dp))
        Text(text = text, style = style)
    }
}