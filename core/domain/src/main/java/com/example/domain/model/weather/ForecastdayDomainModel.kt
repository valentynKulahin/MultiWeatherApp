package com.example.domain.model.weather

data class ForecastdayDomainModel(
    val astro: AstroDomainModel? = AstroDomainModel(),
    val date: String? = "",
    val date_epoch: Int? = 0,
    val day: DayDomainModel? = DayDomainModel(),
    val hour: List<HourDomainModel>? = listOf()
)