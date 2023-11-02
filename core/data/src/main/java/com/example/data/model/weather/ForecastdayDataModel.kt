package com.example.data.model.weather

data class ForecastdayDataModel(
    val astro: AstroDataModel? = AstroDataModel(),
    val date: String? = "",
    val date_epoch: Int? = 0,
    val day: DayDataModel? = DayDataModel(),
    val hour: List<HourDataModel>? = listOf()
)