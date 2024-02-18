package com.example.home

import android.os.Build
import androidx.annotation.RequiresApi
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
import com.example.designsystem.permissions.ExternalStorage_Permission
import com.example.designsystem.permissions.Location_Permissions
import com.example.home.components.CalendarElevatedCard
import com.example.home.components.ForecastElevatedCardDays
import com.example.home.components.ForecastElevatedCardHour
import com.example.home.components.NewsElevatedCard
import com.example.home.components.SunConditionElevatedCard
import com.example.home.components.WeatherElevatedCard
import com.example.home.components.WindElevatedCard
import com.example.model.model.country.CountryItemExternalModel
import com.example.model.model.news.NewsExternalModel
import com.example.model.model.weather.AstroExternalModel
import com.example.model.model.weather.CurrentExternalModel
import com.example.model.model.weather.ForecastExternalModel
import com.example.model.model.weather.LocationExternalModel
import com.example.model.model.weather.WeatherExternalModel
import com.google.accompanist.pager.HorizontalPagerIndicator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.S)
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

    if (uiState.value.isLoading) {
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.fillMaxSize()
        )
    }

    Location_Permissions()
    ExternalStorage_Permission()

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
                        IconButton(onClick = { homeVM.reducer(action = HomeScreenUiAction.NavigateToSearchScreen) }) {
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

@RequiresApi(Build.VERSION_CODES.S)
@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun HomeScreenMain(
    favouritesCountry: List<CountryItemExternalModel>,
    paddingValues: PaddingValues
) {
    val verticalScrollState = rememberScrollState()
    val pagerState = rememberPagerState {
        favouritesCountry.size
    }

    HorizontalPager(state = pagerState) { page ->
        val weatherExternalModel = favouritesCountry[page].weather
        val newsExternalModel = favouritesCountry[page].news

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
                weatherExternalModel = weatherExternalModel
            )
            Spacer(modifier = Modifier.height(10.dp))
            HorizontalPagerIndicator(
                pagerState = pagerState, pageCount = favouritesCountry.size
            )
            Spacer(modifier = Modifier.height(10.dp))
            HomeScreen_NewsCard(news = newsExternalModel)
            Spacer(modifier = Modifier.height(10.dp))
            HomeScreen_ForecastCard_Hour(
                currentWeather = weatherExternalModel.current
                    ?: CurrentExternalModel(),
                forecastWeather = weatherExternalModel.forecast
                    ?: ForecastExternalModel()
            )
            Spacer(modifier = Modifier.height(10.dp))
            HomeScreen_ForecastCard_Days(
                currentWeather = weatherExternalModel.current
                    ?: CurrentExternalModel(),
                forecastWeather = weatherExternalModel.forecast
                    ?: ForecastExternalModel()
            )
            Spacer(modifier = Modifier.height(10.dp))
            HomeScreen_SunCondition(
                currentExternalModel = weatherExternalModel.current
                    ?: CurrentExternalModel(),
                astroExternalModel = if (weatherExternalModel.forecast?.forecastday.isNullOrEmpty()) AstroExternalModel() else weatherExternalModel.forecast?.forecastday?.first()?.astro
                    ?: AstroExternalModel()
            )
            Spacer(modifier = Modifier.height(10.dp))
            HomeScreen_WindCard()
            Spacer(modifier = Modifier.height(10.dp))
            HomeScreen_CalendarCard()
            Spacer(modifier = Modifier.height(10.dp))

            Location_Permissions()
            ExternalStorage_Permission()
        }
    }
}

@Composable
private fun HomeScreen_WeatherCard(
    weatherExternalModel: WeatherExternalModel
) {
    WeatherElevatedCard(
        currentWeather = weatherExternalModel.current ?: CurrentExternalModel(),
        locationExternalModel = weatherExternalModel.location ?: LocationExternalModel()
    )
}

@Composable
private fun HomeScreen_NewsCard(news: NewsExternalModel) {
    NewsElevatedCard(news = news)
}

@Composable
private fun HomeScreen_ForecastCard_Hour(
    currentWeather: CurrentExternalModel,
    forecastWeather: ForecastExternalModel
) {
    ForecastElevatedCardHour(
        currentWeather = currentWeather,
        forecastWeather = forecastWeather
    )
}

@Composable
private fun HomeScreen_ForecastCard_Days(
    currentWeather: CurrentExternalModel,
    forecastWeather: ForecastExternalModel
) {
    ForecastElevatedCardDays(
        currentWeather = currentWeather,
        forecastWeather = forecastWeather
    )
}

@Composable
private fun HomeScreen_SunCondition(
    currentExternalModel: CurrentExternalModel,
    astroExternalModel: AstroExternalModel
) {
    SunConditionElevatedCard(
        currentExternalModel = currentExternalModel,
        astroExternalModel = astroExternalModel
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
