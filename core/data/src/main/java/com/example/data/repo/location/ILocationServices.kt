package com.example.data.repo.location

import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow

interface ILocationServices {

    val myLocation: Flow<LatLng?>

}