package com.example.network.models.weather


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class ForecastdayNetworkModel(
    @SerializedName("astro")
    @Expose
    val astro: AstroNetworkModel? = AstroNetworkModel(),
    @SerializedName("date")
    @Expose
    val date: String? = "",
    @SerializedName("date_epoch")
    @Expose
    val dateEpoch: Int? = 0,
    @SerializedName("day")
    @Expose
    val day: DayNetworkModel? = DayNetworkModel(),
    @SerializedName("hour")
    @Expose
    val hour: List<HourNetworkModel>? = listOf()
)