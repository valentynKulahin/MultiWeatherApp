package com.example.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.designsystem.R
import com.example.domain.model.weather.CurrentDomainModel

@Composable
fun SunConditionElevatedCard(
    currentDomainModel: CurrentDomainModel
) {

    SunCondition_Screen(currentDomainModel = currentDomainModel)

}

@Composable
private fun SunCondition_Screen(currentDomainModel: CurrentDomainModel) {
    Column {
        SunCondition_Header()
        SunCondition_Screen_Card(currentDomainModel = currentDomainModel)
    }
}

@Composable
private fun SunCondition_Screen_Card(currentDomainModel: CurrentDomainModel) {

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(20.dp),
        shape = CardDefaults.elevatedShape
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            SunCondition_Top(currentDomainModel = currentDomainModel)
            SunCondition_Bottom(currentDomainModel = currentDomainModel)
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
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(id = R.string.sun_text))
            Text(text = currentDomainModel.uv.toString(), fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun SunCondition_Bottom(currentDomainModel: CurrentDomainModel) {

}



