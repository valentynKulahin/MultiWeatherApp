package com.example.network.models.weather


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class LocationNetworkModel(
    @SerializedName("country")
    @Expose
    val country: String? = null,
    @SerializedName("lat")
    @Expose
    val lat: Double? = null,
    @SerializedName("localtime")
    @Expose
    val localtime: String? = null,
    @SerializedName("localtime_epoch")
    @Expose
    val localtimeEpoch: Int? = null,
    @SerializedName("lon")
    @Expose
    val lon: Double? = null,
    @SerializedName("name")
    @Expose
    val name: String? = null,
    @SerializedName("region")
    @Expose
    val region: String? = null,
    @SerializedName("tz_id")
    @Expose
    val tzId: String? = null
)