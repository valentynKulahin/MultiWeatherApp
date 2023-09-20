package com.example.domain.model.forecast

import com.example.domain.model.common.Condition

data class Hour(
    val chance_of_rain: Int? = 0,
    val chance_of_snow: Int? = 0,
    val cloud: Int? = 0,
    val condition: Condition? = Condition(),
    val dewpoint_c: Double? = 0.0,
    val dewpoint_f: Double? = 0.0,
    val feelslike_c: Double? = 0.0,
    val feelslike_f: Double? = 0.0,
    val gust_kph: Double? = 0.0,
    val gust_mph: Double? = 0.0,
    val heatindex_c: Double? = 0.0,
    val heatindex_f: Double? = 0.0,
    val humidity: Int? = 0,
    val is_day: Int? = 0,
    val precip_in: Double? = 0.0,
    val precip_mm: Double? = 0.0,
    val pressure_in: Double? = 0.0,
    val pressure_mb: Double? = 0.0,
    val temp_c: Double? = 0.0,
    val temp_f: Double? = 0.0,
    val time: String? = "",
    val time_epoch: Int? = 0,
    val uv: Double? = 0.0,
    val vis_km: Double? = 0.0,
    val vis_miles: Double? = 0.0,
    val will_it_rain: Int? = 0,
    val will_it_snow: Int? = 0,
    val wind_degree: Int? = 0,
    val wind_dir: String? = "",
    val wind_kph: Double? = 0.0,
    val wind_mph: Double? = 0.0,
    val windchill_c: Double? = 0.0,
    val windchill_f: Double? = 0.0
)