package com.example.designsystem.component

import androidx.compose.material3.DrawerState
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope

@Composable
fun WeatherDrawerMenu(navController: NavController, drawerState: DrawerState, scope: CoroutineScope) {
    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet()
        }
    ) {
        Text(text = "Test")
    }
}

@Composable
fun ModalDrawerSheet() {
    Text(text = "Test1")
}

@Composable
fun ModalDrawerSheetItem() {
    Text(text = "Test2")
}