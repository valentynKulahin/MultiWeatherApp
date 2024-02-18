package com.example.designsystem.permissions

import android.Manifest
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.google.accompanist.permissions.*

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Location_Permissions() {
    val multipleLocationPermissionState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_BACKGROUND_LOCATION
        )
    )

    LaunchedEffect(key1 = multipleLocationPermissionState) {
        when (multipleLocationPermissionState.allPermissionsGranted) {
            true -> {}
            false -> {
                multipleLocationPermissionState.launchMultiplePermissionRequest()
            }
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ExternalStorage_Permission() {
    val multipleExternalStoragePermissionState =
        rememberPermissionState(permission = Manifest.permission.READ_EXTERNAL_STORAGE)

    LaunchedEffect(multipleExternalStoragePermissionState) {
        when (multipleExternalStoragePermissionState.status.isGranted) {
            true -> {}
            false -> {
                multipleExternalStoragePermissionState.launchPermissionRequest()
            }
        }
    }
}