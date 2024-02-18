package com.example.model.model.weather

data class ForecastdayExternalModel(
    val astro: AstroExternalModel? = AstroExternalModel(),
    val date: String? = "",
    val date_epoch: Int? = 0,
    val day: DayExternalModel? = DayExternalModel(),
    val hour: List<HourExternalModel>? = listOf()
)