package com.example.domain.model.weather

data class LocationDomainModel(
    val country: String? = null,
    val lat: Double? = null,
    val localtime: String? = null,
    val localtime_epoch: Int? = null,
    val lon: Double? = null,
    val name: String? = null,
    val region: String? = null,
    val tz_id: String? = null
)