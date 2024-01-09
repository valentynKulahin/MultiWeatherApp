package com.example.designsystem.permissions

import android.Manifest
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.*

@RequiresApi(Build.VERSION_CODES.S)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RequestPermissions() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val multipleLocationPermissionState = rememberMultiplePermissionsState(
            permissions = listOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        )
        Location_Permissions(permissionState = multipleLocationPermissionState)
    }

}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Location_Permissions(
    permissionState: MultiplePermissionsState
) {
    LaunchedEffect(permissionState) {
        when (permissionState.allPermissionsGranted) {
            true -> {}
            false -> {
                permissionState.launchMultiplePermissionRequest()
            }
        }
    }
}