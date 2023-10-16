package com.example.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.designsystem.R
import com.example.domain.model.news.NewsResult

@Composable
fun NewsElevatedCard(
    navController: NavHostController,
    news: NewsResult
) {

    Column {
        News_Header()
        News_Cards()
    }

}

@Composable
private fun News_Header() {
    Text(text = stringResource(id = R.string.news_header))
}

@Composable
private fun News_Cards() {
    //pager
    News_Card()
}

@Composable
private fun News_Card() {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(20.dp),
        shape = CardDefaults.elevatedShape
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            NewsCard_Image()
            NewsCard_Header()
            NewsCard_Bottom()
        }
    }
}

@Composable
private fun NewsCard_Image() {

}

@Composable
private fun NewsCard_Header() {

}

@Composable
private fun NewsCard_Bottom() {

}

