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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.BottomSheetDefaults
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
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.common.CommonFun.convertStringToLink
import com.example.designsystem.component.WeatherBackground
import com.example.designsystem.component.WeatherTopAppBar
import com.example.domain.model.country.CountryItemDomainModel
import com.example.domain.model.weather.CurrentDomainModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    searchVM: SearchScreenViewModel = hiltViewModel(),
    snackbarState: SnackbarHostState,
    snackbarText: String
) {

    val uiState = searchVM.uiState.collectAsStateWithLifecycle()
    val location =
        if (uiState.value.countryForSearch.lat != null || uiState.value.countryForSearch.lon != null) LatLng(
            uiState.value.countryForSearch.lat!!.toDouble(),
            uiState.value.countryForSearch.lon!!.toDouble()
        ) else LatLng(0.0, 0.0)

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
                location = location,
                searchVM = searchVM,
                countriesList = uiState.value.countriesList,
                countryForSearch = uiState.value.countryForSearch,
                currentDomainModel = uiState.value.currentDomainModel,
                inFavourite = uiState.value.countryInFavourite,
                onClickMyLocation = {
                    searchVM.reducer(
                        intent = SearchScreenIntent.GetWeatherInCountryByLatLon(
                            latLon = LatLng(it.latitude, it.longitude)
                        )
                    )
                },
                onMapClick = {
                    searchVM.reducer(
                        intent = SearchScreenIntent.GetWeatherInCountryByLatLon(
                            latLon = LatLng(it.latitude, it.longitude)
                        )
                    )
                },
                onClickAddCountryToFavourite = {
                    searchVM.reducer(intent = SearchScreenIntent.AddToFavourite)
                },
                onClickDeleteCountryFromFavourite = {
                    searchVM.reducer(intent = SearchScreenIntent.DeleteFromFavourite)
                }
            )
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchScreenMain(
    paddingValues: PaddingValues,
    location: LatLng,
    searchVM: SearchScreenViewModel,
    countriesList: List<CountryItemDomainModel>,
    countryForSearch: CountryItemDomainModel,
    currentDomainModel: CurrentDomainModel,
    inFavourite: Boolean,
    onClickMyLocation: (LatLng) -> Unit,
    onMapClick: (LatLng) -> Unit,
    onClickAddCountryToFavourite: () -> Unit,
    onClickDeleteCountryFromFavourite: () -> Unit
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
                searchVM.reducer(intent = SearchScreenIntent.UpdateCountryForSearch(searchingItem = it))
                searchVM.reducer(intent = SearchScreenIntent.GetWeatherInCountry(searchingValue = it.name.toString()))
                expanded.value = false
                searchingValue.value = it.name.toString()
                showSheet.value = true
            },
            countriesList = countriesList
        )
        Spacer(modifier = Modifier.height(10.dp))
        SearchScreen_GoogleMaps(
            location = location,
            showSheet = showSheet,
            onClickMyLocation = onClickMyLocation,
            onMapClick = onMapClick
        )
        if (showSheet.value) {
            SearchScreen_BottomSheet(
                showSheet = showSheet,
                location = location,
                bottomSheetState = sheetState,
                countryForSearch = countryForSearch,
                currentDomainModel = currentDomainModel,
                inFavourite = inFavourite,
                addToFavouriteClick = { onClickAddCountryToFavourite() },
                deleteFromFavouriteClick = { onClickDeleteCountryFromFavourite() }
            )
        }
    }
}

@Composable
private fun SearchScreen_Find(
    searchingValue: MutableState<String>,
    expanded: MutableState<Boolean>,
    onSearch: () -> Unit = {},
    onClickDropDownItem: (CountryItemDomainModel) -> Unit = {},
    countriesList: List<CountryItemDomainModel> = emptyList()
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
private fun SearchScreen_GoogleMaps(
    location: LatLng,
    showSheet: MutableState<Boolean>,
    onClickMyLocation: (LatLng) -> Unit,
    onMapClick: (LatLng) -> Unit
) {
    val scope: CoroutineScope = rememberCoroutineScope()
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(location, 5F)
    }
    val markerState = MarkerState(position = location)
    val uiSettings = remember {
        mutableStateOf(
            MapUiSettings(
                zoomControlsEnabled = true,
                myLocationButtonEnabled = true
            )
        )
    }
    val properties = remember {
        mutableStateOf(
            MapProperties(
                mapType = MapType.HYBRID,
                isMyLocationEnabled = true
            )
        )
    }

    LaunchedEffect(location) {
        if (location.latitude != 0.0 && location.longitude != 0.0)
            scope.launch(context = Dispatchers.Main) {
                cameraPositionState.position = CameraPosition.fromLatLngZoom(location, 10F)
            }
    }

    GoogleMap(
        cameraPositionState = cameraPositionState,
        uiSettings = uiSettings.value,
        properties = properties.value,
        onMyLocationClick = {
            onClickMyLocation(LatLng(it.latitude, it.longitude))
            showSheet.value = true
        },
        onMapClick = {
            onMapClick(LatLng(it.latitude, it.longitude))
            showSheet.value = true
        }
    ) {
        if (location.latitude != 0.0 && location.longitude != 0.0) {
            Marker(
                contentDescription = null,
                state = markerState,
                title = "position",
                draggable = true,
                onInfoWindowClick = {
                    showSheet.value = !showSheet.value
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchScreen_BottomSheet(
    showSheet: MutableState<Boolean>,
    location: LatLng,
    bottomSheetState: SheetState,
    countryForSearch: CountryItemDomainModel,
    currentDomainModel: CurrentDomainModel,
    inFavourite: Boolean,
    addToFavouriteClick: () -> Unit,
    deleteFromFavouriteClick: () -> Unit
) {

    ModalBottomSheet(
        onDismissRequest = { showSheet.value = false },
        modifier = Modifier.fillMaxWidth(),
        sheetState = bottomSheetState,
        shape = MaterialTheme.shapes.small,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        SearchScreen_BottomSheet_Weather(
            countryForSearch = countryForSearch,
            currentDomainModel = currentDomainModel,
            location = location,
            wind = currentDomainModel.wind_mph.toString(),
            inFavourite = inFavourite,
            addToFavouriteClick = { addToFavouriteClick() },
            deleteFromFavouriteClick = { deleteFromFavouriteClick() }
        )
    }

}

@Composable
private fun SearchScreen_BottomSheet_Weather(
    location: LatLng,
    wind: String,
    countryForSearch: CountryItemDomainModel,
    currentDomainModel: CurrentDomainModel,
    inFavourite: Boolean,
    addToFavouriteClick: () -> Unit,
    deleteFromFavouriteClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchScreen_BottomSheet_Weather_FirstLine(
            countryForSearch = countryForSearch,
            currentDomainModel = currentDomainModel
        )
        Divider()
        SearchScreen_BottomSheet_Weather_SecondLine(location = location, wind = wind)
        Divider()
        SearchScreen_BottomSheet_Weather_FavouriteButton(
            inFavourite = inFavourite,
            addToFavouriteClick = { addToFavouriteClick() },
            deleteFromFavouriteClick = { deleteFromFavouriteClick() }
        )
    }

}

@Composable
private fun SearchScreen_BottomSheet_Weather_FirstLine(
    countryForSearch: CountryItemDomainModel,
    currentDomainModel: CurrentDomainModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SearchScreen_BottomSheet_Weather_FirstLine_Left(countryForSearch = countryForSearch)
        SearchScreen_BottomSheet_Weather_FirstLine_Right(currentDomainModel = currentDomainModel)
    }
}

@Composable
private fun SearchScreen_BottomSheet_Weather_FirstLine_Left(
    countryForSearch: CountryItemDomainModel
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(imageVector = Icons.Default.LocationOn, contentDescription = null)
        Column {
            Text(text = countryForSearch.name.toString())
            Text(
                text = "${countryForSearch.country.toString()}, ${countryForSearch.region.toString()}",
                color = Color.LightGray
            )
        }
    }
}

@Composable
private fun SearchScreen_BottomSheet_Weather_FirstLine_Right(
    currentDomainModel: CurrentDomainModel
) {
    val iconURL = convertStringToLink(currentDomainModel.condition?.icon.toString())

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        AsyncImage(
            model = iconURL,
            contentDescription = "weather",
            modifier = Modifier.size(70.dp)
        )
        Text(
            text = "${currentDomainModel.temp_c} \u2103",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
        )
    }
}

@Composable
private fun SearchScreen_BottomSheet_Weather_SecondLine(
    location: LatLng,
    wind: String
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        SearchScreen_BottomSheet_Weather_SecondLine_Latitude(location = location)
        SearchScreen_BottomSheet_Weather_SecondLine_Wind(wind = wind)
    }

}

@Composable
private fun SearchScreen_BottomSheet_Weather_SecondLine_Latitude(
    location: LatLng
) {

    Column {
        Text(text = "Latitude and longtitude")
        Text(
            text = "${location.latitude}, ${location.longitude}",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold
        )
    }

}

@Composable
private fun SearchScreen_BottomSheet_Weather_SecondLine_Wind(
    wind: String
) {

    Column {
        Text(text = "Wind")
        Text(
            text = "$wind mp/h",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold
        )
    }

}

@Composable
fun SearchScreen_BottomSheet_Weather_FavouriteButton(
    inFavourite: Boolean,
    addToFavouriteClick: () -> Unit,
    deleteFromFavouriteClick: () -> Unit
) {

    OutlinedButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp),
        onClick = { if (inFavourite) {
            addToFavouriteClick()
        } else {
            deleteFromFavouriteClick()
        } }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = if (inFavourite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = null
            )
            Text(text = if (inFavourite) "Add to favourite" else "Delete from favourite")
        }
    }

}