package com.example.domain.model.weather

data class AirQualityDomainModel(
    val co: Double? = null,
    val gb_defra_index: Int? = null,
    val no2: Double? = null,
    val o3: Double? = null,
    val pm10: Double? = null,
    val pm2_5: Double? = null,
    val so2: Double? = null,
    val us_epa_index: Int? = null
)