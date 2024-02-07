package com.example.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.designsystem.component.WeatherBackground
import com.example.designsystem.component.WeatherTopAppBar
import com.example.domain.model.country.CountryItemDomainModel
import com.example.domain.model.news.NewsDomainModel
import com.example.domain.model.weather.AstroDomainModel
import com.example.domain.model.weather.CurrentDomainModel
import com.example.domain.model.weather.ForecastDomainModel
import com.example.domain.model.weather.LocationDomainModel
import com.example.domain.model.weather.WeatherDomainModel
import com.example.home.components.CalendarElevatedCard
import com.example.home.components.ForecastElevatedCardDays
import com.example.home.components.ForecastElevatedCardHour
import com.example.home.components.NewsElevatedCard
import com.example.home.components.SunConditionElevatedCard
import com.example.home.components.WeatherElevatedCard
import com.example.home.components.WindElevatedCard
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeVM: HomeScreenViewModel = hiltViewModel(),
    scope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState,
    snackbarState: SnackbarHostState,
    snackbarText: String
) {
    val uiState = homeVM.uiState.collectAsStateWithLifecycle()

    if (uiState.value.loading) {
        CircularProgressIndicator(modifier = Modifier.fillMaxSize())
    }

    WeatherBackground(
        modifier = Modifier
            .wrapContentHeight()
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                WeatherTopAppBar(
                    modifier = Modifier.fillMaxWidth(),
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background),
                    title = {
                        Text(
                            text = stringResource(id = R.string.home),
                            maxLines = 1,
                            overflow = TextOverflow.Clip
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch(context = Dispatchers.IO) {
                                    if (drawerState.isClosed) drawerState.open() else drawerState.close()
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = null
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { homeVM.reducer(intent = HomeScreenIntent.NavigateToSearchScreen) }) {
                            Icon(imageVector = Icons.Default.Search, contentDescription = null)
                        }
                    }
                )
            },
            snackbarHost = {
                SnackbarHost(
                    hostState = snackbarState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                ) {
                    Snackbar {
                        Text(text = snackbarText)
                    }
                }
            },
            floatingActionButton = { },
            floatingActionButtonPosition = FabPosition.End
        ) { padding ->
            HomeScreenMain(
                favouritesCountry = uiState.value.favouritesCountry,
                paddingValues = padding
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun HomeScreenMain(
    favouritesCountry: List<CountryItemDomainModel>,
    paddingValues: PaddingValues
) {
    val verticalScrollState = rememberScrollState()
    val pagerState = rememberPagerState {
        favouritesCountry.size
    }

    HorizontalPager(state = pagerState) { page ->
        val weatherDomainModel = favouritesCountry[page].weather
        val newsDomainModel = favouritesCountry[page].news

        Column(
            modifier = Modifier
                .padding(paddingValues = paddingValues)
                .wrapContentHeight()
                .verticalScroll(
                    state = verticalScrollState,
                    enabled = true
                )
        ) {
            HomeScreen_WeatherCard(
                weatherDomainModel = weatherDomainModel
            )
            Spacer(modifier = Modifier.height(10.dp))
            HorizontalPagerIndicator(
                pagerState = pagerState, pageCount = favouritesCountry.size
            )
            Spacer(modifier = Modifier.height(10.dp))
            HomeScreen_NewsCard(news = newsDomainModel)
            Spacer(modifier = Modifier.height(10.dp))
            HomeScreen_ForecastCard_Hour(
                currentWeather = weatherDomainModel.current
                    ?: CurrentDomainModel(),
                forecastWeather = weatherDomainModel.forecast
                    ?: ForecastDomainModel()
            )
            Spacer(modifier = Modifier.height(10.dp))
            HomeScreen_ForecastCard_Days(
                currentWeather = weatherDomainModel.current
                    ?: CurrentDomainModel(),
                forecastWeather = weatherDomainModel.forecast
                    ?: ForecastDomainModel()
            )
            Spacer(modifier = Modifier.height(10.dp))
            HomeScreen_SunCondition(
                currentDomainModel = weatherDomainModel.current
                    ?: CurrentDomainModel(),
                astroDomainModel = if (weatherDomainModel.forecast?.forecastday.isNullOrEmpty()) AstroDomainModel() else weatherDomainModel.forecast?.forecastday?.first()?.astro
                    ?: AstroDomainModel()
            )
            Spacer(modifier = Modifier.height(10.dp))
            HomeScreen_WindCard()
            Spacer(modifier = Modifier.height(10.dp))
            HomeScreen_CalendarCard()
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
private fun HomeScreen_WeatherCard(
    weatherDomainModel: WeatherDomainModel
) {
    WeatherElevatedCard(
        currentWeather = weatherDomainModel.current ?: CurrentDomainModel(),
        locationDomainModel = weatherDomainModel.location ?: LocationDomainModel()
    )
}

@Composable
private fun HomeScreen_NewsCard(news: NewsDomainModel) {
    NewsElevatedCard(news = news)
}

@Composable
private fun HomeScreen_ForecastCard_Hour(
    currentWeather: CurrentDomainModel,
    forecastWeather: ForecastDomainModel
) {
    ForecastElevatedCardHour(
        currentWeather = currentWeather,
        forecastWeather = forecastWeather
    )
}

@Composable
private fun HomeScreen_ForecastCard_Days(
    currentWeather: CurrentDomainModel,
    forecastWeather: ForecastDomainModel
) {
    ForecastElevatedCardDays(
        currentWeather = currentWeather,
        forecastWeather = forecastWeather
    )
}

@Composable
private fun HomeScreen_SunCondition(
    currentDomainModel: CurrentDomainModel,
    astroDomainModel: AstroDomainModel
) {
    SunConditionElevatedCard(
        currentDomainModel = currentDomainModel,
        astroDomainModel = astroDomainModel
    )
}

@Composable
private fun HomeScreen_WindCard() {
    WindElevatedCard()
}

@Composable
private fun HomeScreen_CalendarCard() {
    CalendarElevatedCard()
}
