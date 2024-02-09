package com.example.designsystem.permissions

import android.Manifest
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.google.accompanist.permissions.*

@RequiresApi(Build.VERSION_CODES.S)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RequestPermissions() {

//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(30.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
        val multipleLocationPermissionState = rememberMultiplePermissionsState(
            permissions = listOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_BACKGROUND_LOCATION
            )
        )
        Location_Permissions(permissionState = multipleLocationPermissionState)

        val multipleExternalStoragePermissionState =
            rememberMultiplePermissionsState(permissions = listOf(Manifest.permission.READ_EXTERNAL_STORAGE))
        ExternalStorage_Permissions(permissionState = multipleExternalStoragePermissionState)
//    }

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

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ExternalStorage_Permissions(
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