package com.example.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.common.model.search.SearchResultItem
import com.example.designsystem.component.WeatherBackground
import com.example.designsystem.component.WeatherTopAppBar
import com.example.domain.model.weather.ForecastDomainModel
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    searchVM: SearchScreenViewModel = hiltViewModel(),
    snackbarState: SnackbarHostState,
    snackbarText: String
) {

    val uiState = searchVM.uiState.collectAsStateWithLifecycle()

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
                            text = "Search",
                            maxLines = 1,
                            overflow = TextOverflow.Clip
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                searchVM.reducer(intent = SearchScreenIntent.NavigateBack)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = null
                            )
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
            SearchScreenMain(
                paddingValues = padding,
                searchVM = searchVM,
                countriesList = uiState.value.countriesList,
                cityValue = uiState.value.cityValue,
                forecastDomainModel = uiState.value.forecastDomainModel
            )
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchScreenMain(
    paddingValues: PaddingValues,
    searchVM: SearchScreenViewModel,
    countriesList: List<SearchResultItem>,
    cityValue: SearchResultItem,
    forecastDomainModel: ForecastDomainModel
) {
    val searchingValue = remember { mutableStateOf("") }
    val expanded = remember { mutableStateOf(false) }
    val showSheet = remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        SearchScreen_Find(
            searchingValue = searchingValue,
            expanded = expanded,
            onSearch = {
                searchVM.reducer(
                    intent = SearchScreenIntent.UpdateSearchingName(
                        seachingValue = searchingValue.value
                    )
                )
                searchVM.reducer(intent = SearchScreenIntent.GetSearchingCountriesList)
            },
            onClickDropDownItem = {
                searchVM.reducer(intent = SearchScreenIntent.UpdateSearchingName(seachingValue = it.name.toString()))
                searchVM.reducer(intent = SearchScreenIntent.UpdateCityValue(searchingItem = it))
                expanded.value = false
            },
            countriesList = countriesList
        )
        Spacer(modifier = Modifier.height(10.dp))
        SearchScreen_GoogleMaps()
        if (showSheet.value) {
            SearchScreen_BottomSheet(showSheet = showSheet, bottomSheetState = sheetState, cityValue = cityValue, forecastDomainModel = forecastDomainModel)
        }
    }
}

@Composable
private fun SearchScreen_Find(
    searchingValue: MutableState<String>,
    expanded: MutableState<Boolean>,
    onSearch: () -> Unit = {},
    onClickDropDownItem: (SearchResultItem) -> Unit = {},
    countriesList: List<SearchResultItem> = emptyList()
) {
    var mTextFieldSize by remember { mutableStateOf(Size.Zero) }
    val icon = if (expanded.value)
        Icons.Filled.ArrowDropUp
    else
        Icons.Filled.ArrowDropDown

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = searchingValue.value,
            onValueChange = {
                searchingValue.value = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .onGloballyPositioned { coordinates ->
                    // This value is used to assign to
                    // the DropDown the same width
                    mTextFieldSize = coordinates.size.toSize()
                },
            enabled = true,
            singleLine = true,
            readOnly = false,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(onSearch = {
                onSearch()
                expanded.value = true
            }),
            placeholder = { Text(text = "Enter city") },
            trailingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = Color.Transparent,
                    modifier = Modifier.clickable { expanded.value = !expanded.value }
                )
            }
        )
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })
        ) {
            countriesList.forEach { item ->
                DropdownMenuItem(
                    colors = MenuDefaults.itemColors(),
                    text = {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(text = item.name.toString())
                            Text(
                                text = "${item.country.toString()}, ${item.region.toString()}",
                                color = Color.Red
                            )
                        }
                    },
                    onClick = { onClickDropDownItem(item) }
                )
            }
        }
    }
}

@Composable
private fun SearchScreen_GoogleMaps() {
    val cameraPositionState = rememberCameraPositionState()

    GoogleMap(
        cameraPositionState = cameraPositionState,
        uiSettings = MapUiSettings(),
        properties = MapProperties(
            isMyLocationEnabled = true,
            isBuildingEnabled = true
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchScreen_BottomSheet(
    showSheet: MutableState<Boolean>,
    bottomSheetState: SheetState,
    cityValue: SearchResultItem,
    forecastDomainModel: ForecastDomainModel
) {

    ModalBottomSheet(
        onDismissRequest = { showSheet.value = false },
        modifier = Modifier.fillMaxWidth(),
        sheetState = bottomSheetState,
        shape = MaterialTheme.shapes.small
    ) {
        SearchScreen_BottomSheet_Weather(
            cityValue = cityValue,
            forecastDomainModel = forecastDomainModel
        )
    }

}

@Composable
private fun SearchScreen_BottomSheet_Weather(
    cityValue: SearchResultItem,
    forecastDomainModel: ForecastDomainModel
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        SearchScreen_BottomSheet_Weather_FirstLine(
            cityValue = cityValue,
            forecastDomainModel = forecastDomainModel
        )
        Divider()
        SearchScreen_BottomSheet_Weather_SecondLine()
    }

}

@Composable
private fun SearchScreen_BottomSheet_Weather_FirstLine(
    cityValue: SearchResultItem,
    forecastDomainModel: ForecastDomainModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

    }
}

@Composable
private fun SearchScreen_BottomSheet_Weather_FirstLine_Left(
    cityValue: SearchResultItem
) {
    Icon(imageVector = Icons.Default.LocationOn, contentDescription = null)
    Column {
        Text(text = cityValue.name.toString())
        Text(
            text = "${cityValue.country.toString()}, ${cityValue.region.toString()}",
            color = Color.LightGray
        )
    }
}

@Composable
private fun SearchScreen_BottomSheet_Weather_FirstLine_Right(
    forecastDomainModel: ForecastDomainModel
) {

}

@Composable
private fun SearchScreen_BottomSheet_Weather_SecondLine() {

}