package com.example.location

import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationScreen(
    locationVM: LocationScreenViewModel = hiltViewModel(),
    scope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState,
    snackbarState: SnackbarHostState,
    snackbarText: String
) {



}