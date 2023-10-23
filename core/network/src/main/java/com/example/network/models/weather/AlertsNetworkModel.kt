package com.example.network.models.weather


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class AlertsNetworkModel(
    @SerializedName("alerts")
    @Expose
    val alerts: AlertNetworkModel? = AlertNetworkModel()
)