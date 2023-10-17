package com.example.home.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.designsystem.R
import com.example.domain.model.news.Article
import com.example.domain.model.news.NewsResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun NewsElevatedCard(
    navController: NavHostController,
    news: NewsResult,
    scope: CoroutineScope = rememberCoroutineScope()
) {

    Column {
        News_Header()
        News_Cards(news = news.articles, scope = scope)
    }

}

@Composable
private fun News_Header() {
    Text(
        modifier = Modifier.padding(horizontal = 10.dp),
        text = stringResource(id = R.string.news_header)
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun News_Cards(
    news: List<Article?>?,
    scope: CoroutineScope
) {
    val pagerState = rememberPagerState(pageCount = { news?.size ?: 0 })

    HorizontalPager(state = pagerState) {
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            News_Card(article = news?.get(it) ?: Article())
        }
    }
    Row(
        Modifier
            .height(15.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom
    ) {
        repeat(news?.size ?: 0) { iteration ->
            val color =
                if (pagerState.currentPage == iteration) MaterialTheme.colorScheme.primary else Color.LightGray
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .clip(CircleShape)
                    .background(color)
                    .size(10.dp)
                    .clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = null
                    ) {
                        scope.launch { pagerState.animateScrollToPage(iteration) }
                    }
            )
        }
    }
}

@Composable
private fun News_Card(article: Article?) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp)
            .padding(20.dp),
        shape = CardDefaults.elevatedShape
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            NewsCard_Image(imageURL = article?.urlToImage ?: "")
            NewsCard_Header(headerText = article?.title ?: "")
            NewsCard_Bottom(
                publishedTime = article?.publishedAt ?: "",
                nameSource = article?.source?.name ?: ""
            )
        }
    }
}

@Composable
private fun NewsCard_Image(
    imageURL: String
) {
    AsyncImage(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp),
        model = imageURL,
        contentDescription = null
    )
}

@Composable
private fun NewsCard_Header(
    headerText: String
) {
    Text(
        modifier = Modifier.padding(10.dp),
        text = headerText,
        style = MaterialTheme.typography.titleMedium,
        maxLines = 2
    )
}

@Composable
private fun NewsCard_Bottom(
    publishedTime: String,
    nameSource: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = publishedTime.replace("T", " ").replace("Z", " "))
        Text(text = nameSource)
    }
}

