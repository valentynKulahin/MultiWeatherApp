package com.example.network.models.weather


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class WeatherNetworkModel(
    @SerializedName("alerts")
    @Expose
    val alerts: AlertsNetworkModel? = AlertsNetworkModel(),
    @SerializedName("current")
    @Expose
    val current: CurrentNetworkModel? = CurrentNetworkModel(),
    @SerializedName("forecast")
    @Expose
    val forecast: ForecastNetworkModel? = ForecastNetworkModel(),
    @SerializedName("location")
    @Expose
    val location: LocationNetworkModel? = LocationNetworkModel()
)