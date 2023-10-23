package com.example.network.models.weather


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class CurrentNetworkModel(
    @SerializedName("air_quality")
    @Expose
    val airQuality: AirQualityNetworkModel? = null,
    @SerializedName("cloud")
    @Expose
    val cloud: Int? = null,
    @SerializedName("condition")
    @Expose
    val condition: ConditionNetworkModel? = null,
    @SerializedName("feelslike_c")
    @Expose
    val feelslikeC: Double? = null,
    @SerializedName("feelslike_f")
    @Expose
    val feelslikeF: Double? = null,
    @SerializedName("gust_kph")
    @Expose
    val gustKph: Double? = null,
    @SerializedName("gust_mph")
    @Expose
    val gustMph: Double? = null,
    @SerializedName("humidity")
    @Expose
    val humidity: Int? = null,
    @SerializedName("is_day")
    @Expose
    val isDay: Int? = null,
    @SerializedName("last_updated")
    @Expose
    val lastUpdated: String? = null,
    @SerializedName("last_updated_epoch")
    @Expose
    val lastUpdatedEpoch: Int? = null,
    @SerializedName("precip_in")
    @Expose
    val precipIn: Double? = null,
    @SerializedName("precip_mm")
    @Expose
    val precipMm: Double? = null,
    @SerializedName("pressure_in")
    @Expose
    val pressureIn: Double? = null,
    @SerializedName("pressure_mb")
    @Expose
    val pressureMb: Double? = null,
    @SerializedName("temp_c")
    @Expose
    val tempC: Double? = null,
    @SerializedName("temp_f")
    @Expose
    val tempF: Double? = null,
    @SerializedName("uv")
    @Expose
    val uv: Double? = null,
    @SerializedName("vis_km")
    @Expose
    val visKm: Double? = null,
    @SerializedName("vis_miles")
    @Expose
    val visMiles: Double? = null,
    @SerializedName("wind_degree")
    @Expose
    val windDegree: Int? = null,
    @SerializedName("wind_dir")
    @Expose
    val windDir: String? = null,
    @SerializedName("wind_kph")
    @Expose
    val windKph: Double? = null,
    @SerializedName("wind_mph")
    @Expose
    val windMph: Double? = null
)