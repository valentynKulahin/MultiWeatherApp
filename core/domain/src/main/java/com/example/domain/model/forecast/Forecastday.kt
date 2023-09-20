package com.example.domain.model.forecast

import com.example.domain.model.common.Astro

data class Forecastday(
    val astro: Astro? = Astro(),
    val date: String? = "",
    val date_epoch: Int? = 0,
    val day: Day? = Day(),
    val hour: List<Hour>? = listOf()
)