package com.example.domain.model.common

data class Astro(
    val is_moon_up: Int? = null,
    val is_sun_up: Int? = null,
    val moon_illumination: String? = null,
    val moon_phase: String? = null,
    val moonrise: String? = null,
    val moonset: String? = null,
    val sunrise: String? = null,
    val sunset: String? = null
)