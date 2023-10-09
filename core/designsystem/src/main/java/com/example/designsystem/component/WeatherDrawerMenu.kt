package com.example.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddLocationAlt
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.DrawerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.designsystem.R
import kotlinx.coroutines.CoroutineScope

@Composable
fun WeatherDrawerMenu(
    navController: NavController,
    drawerState: DrawerState,
    scope: CoroutineScope = rememberCoroutineScope(),
    currentLocation: String,
    favouriteLocations: List<Any>
) {
    ModalDrawerSheet(
        drawerContainerColor = MaterialTheme.colorScheme.primary,
        drawerContentColor = MaterialTheme.colorScheme.onPrimary
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(start = 8.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            WeatherDrawerMenu_Top(
                navController = navController,
                drawerState = drawerState,
                scope = scope,
                currentLocation = currentLocation
            )
            WeatherDrawerMenu_Center(
                navController = navController,
                drawerState = drawerState,
                scope = scope,
                favouriteLocations = favouriteLocations
            )
            WeatherDrawerMenu_Bottom()
        }
    }
}

@Composable
private fun WeatherDrawerMenu_Top(
    navController: NavController,
    drawerState: DrawerState,
    scope: CoroutineScope,
    currentLocation: String
) {
    Column(modifier = Modifier.padding(10.dp)) {
        Text(text = stringResource(id = R.string.drawer_sheet_current_location))
        Spacer(modifier = Modifier.height(6.dp))
        WeatherLocationText(
            imageVector = Icons.Default.LocationOn,
            text = currentLocation,
            spacer = 6,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Composable
private fun WeatherDrawerMenu_Center(
    navController: NavController,
    drawerState: DrawerState,
    scope: CoroutineScope,
    favouriteLocations: List<Any>
) {
    Column(modifier = Modifier
        .wrapContentHeight()
        .padding(horizontal = 10.dp, vertical = 10.dp)) {
        WeatherLocationText(
            imageVector = Icons.Default.AddLocationAlt,
            text = stringResource(id = R.string.drawer_sheet_add_location),
            spacer = 6,
            style = TextStyle(
                color = Color.Yellow,
                fontSize = 23.sp,
                fontWeight = FontWeight.SemiBold
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        WeatherLocationText(
            imageVector = Icons.Default.LocationOn,
            text = stringResource(id = R.string.drawer_sheet_add_location),
            spacer = 6,
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
        )
        Spacer(modifier = Modifier.height(10.dp))
        WeatherLocationText(
            imageVector = Icons.Default.LocationOn,
            text = stringResource(id = R.string.drawer_sheet_add_location),
            spacer = 6,
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
        )
        Spacer(modifier = Modifier.height(10.dp))
        WeatherLocationText(
            imageVector = Icons.Default.LocationOn,
            text = stringResource(id = R.string.drawer_sheet_add_location),
            spacer = 6,
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
        )
    }
}

@Composable
private fun WeatherDrawerMenu_Bottom() {
    Column(modifier = Modifier.padding(top = 10.dp, bottom = 50.dp, start = 10.dp, end = 10.dp)) {
        Text(text = stringResource(id = R.string.drawer_sheet_settings))
    }
}