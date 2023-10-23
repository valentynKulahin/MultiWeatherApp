package com.example.network.models.weather


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class ForecastNetworkModel(
    @SerializedName("forecastday")
    @Expose
    val forecastday: List<ForecastdayNetworkModel>? = listOf()
)