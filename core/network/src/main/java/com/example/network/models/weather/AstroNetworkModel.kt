package com.example.network.models.weather


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class AstroNetworkModel(
    @SerializedName("is_moon_up")
    @Expose
    val isMoonUp: Int? = null,
    @SerializedName("is_sun_up")
    @Expose
    val isSunUp: Int? = null,
    @SerializedName("moon_illumination")
    @Expose
    val moonIllumination: String? = null,
    @SerializedName("moon_phase")
    @Expose
    val moonPhase: String? = null,
    @SerializedName("moonrise")
    @Expose
    val moonrise: String? = null,
    @SerializedName("moonset")
    @Expose
    val moonset: String? = null,
    @SerializedName("sunrise")
    @Expose
    val sunrise: String? = null,
    @SerializedName("sunset")
    @Expose
    val sunset: String? = null
)