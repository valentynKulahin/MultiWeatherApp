package com.example.network.models.weather


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class AirQualityNetworkModel(
    @SerializedName("co")
    @Expose
    val co: Double? = null,
    @SerializedName("gb-defra-index")
    @Expose
    val gbDefraIndex: Int? = null,
    @SerializedName("no2")
    @Expose
    val no2: Double? = null,
    @SerializedName("o3")
    @Expose
    val o3: Double? = null,
    @SerializedName("pm10")
    @Expose
    val pm10: Double? = null,
    @SerializedName("pm2_5")
    @Expose
    val pm25: Double? = null,
    @SerializedName("so2")
    @Expose
    val so2: Double? = null,
    @SerializedName("us-epa-index")
    @Expose
    val usEpaIndex: Int? = null
)