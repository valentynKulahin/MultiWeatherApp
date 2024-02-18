package com.example.model.model.weather

data class CurrentExternalModel(
    val air_quality: AirQualityExternalModel? = null,
    val cloud: Int? = null,
    val condition: ConditionExternalModel? = null,
    val feelslike_c: Double? = null,
    val feelslike_f: Double? = null,
    val gust_kph: Double? = null,
    val gust_mph: Double? = null,
    val humidity: Int? = null,
    val is_day: Int? = null,
    val last_updated: String? = null,
    val last_updated_epoch: Int? = null,
    val precip_in: Double? = null,
    val precip_mm: Double? = null,
    val pressure_in: Double? = null,
    val pressure_mb: Double? = null,
    val temp_c: Double? = null,
    val temp_f: Double? = null,
    val uv: Double? = null,
    val vis_km: Double? = null,
    val vis_miles: Double? = null,
    val wind_degree: Int? = null,
    val wind_dir: String? = null,
    val wind_kph: Double? = null,
    val wind_mph: Double? = null
)