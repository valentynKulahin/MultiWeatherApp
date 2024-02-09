package com.example.domain.usecase

import com.example.data.repo.location.ILocationServices
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocationUseCase @Inject constructor(
    private val locationService: ILocationServices
) {

    operator fun invoke(): Flow<LatLng?> {
        return locationService.myLocation
    }

}