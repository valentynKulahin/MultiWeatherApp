package com.example.home.components

import android.content.ComponentName
import android.net.Uri
import androidx.browser.customtabs.CustomTabsClient
import androidx.browser.customtabs.CustomTabsIntent
import androidx.browser.customtabs.CustomTabsServiceConnection
import androidx.browser.customtabs.CustomTabsSession
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.designsystem.R
import com.example.domain.model.news.ArticleDomainModel
import com.example.domain.model.news.NewsDomainModel
import com.example.home.HomeScreenViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun NewsElevatedCard(
    navController: NavHostController,
    news: NewsDomainModel,
    scope: CoroutineScope = rememberCoroutineScope(),
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
) {

    News_Screen(
        navController = navController,
        news = news,
        scope = scope,
        homeScreenViewModel = homeScreenViewModel
    )

}

@Composable
private fun News_Screen(
    navController: NavHostController,
    news: NewsDomainModel,
    scope: CoroutineScope,
    homeScreenViewModel: HomeScreenViewModel
) {

    val newsLink = remember { mutableStateOf("") }
    val openLink = remember { mutableStateOf(false) }

    Column {
        News_Header()
        News_Cards(
            news = news.articles,
            scope = scope,
            onClick = { newsLink.value = it }
        )
    }

    LaunchedEffect(newsLink.value) {
        openLink.value = true
    }

    if (openLink.value && newsLink.value.isNotEmpty()) {
        NewsCard_OpenLink(link = newsLink.value)
        openLink.value = false
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
    news: List<ArticleDomainModel?>?,
    scope: CoroutineScope,
    onClick: (String) -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { news?.size ?: 0 })

    HorizontalPager(state = pagerState) {
        Row(
            modifier = Modifier.clickable(
                interactionSource = MutableInteractionSource(),
                indication = null,
                enabled = true,
                onClick = {
                    onClick(news?.get(it)?.url ?: "")
                }
            ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            News_Card(article = news?.get(it) ?: ArticleDomainModel())
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
private fun News_Card(article: ArticleDomainModel?) {
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
                nameSource = article?.author ?: article?.source?.name.toString()
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

@Composable
private fun NewsCard_OpenLink(link: String) {

    val context = LocalContext.current
    var mCustomTabsServiceConnection: CustomTabsServiceConnection?
    var mClient: CustomTabsClient?
    var mCustomTabsSession: CustomTabsSession? = null
    mCustomTabsServiceConnection = object : CustomTabsServiceConnection() {
        override fun onCustomTabsServiceConnected(
            componentName: ComponentName,
            customTabsClient: CustomTabsClient
        ) {
            //Pre-warming
            mClient = customTabsClient
            mClient?.warmup(0L)
            mCustomTabsSession = mClient?.newSession(null)
        }

        override fun onServiceDisconnected(name: ComponentName) {
            mClient = null
        }
    }
    CustomTabsClient.bindCustomTabsService(
        context,
        "com.android.chrome",
        mCustomTabsServiceConnection
    )
    val customTabsIntent = CustomTabsIntent.Builder(mCustomTabsSession)
        //.setToolbarColor(color)
        .setShowTitle(true)
        .build()

    customTabsIntent.launchUrl(context, Uri.parse(link))

}
