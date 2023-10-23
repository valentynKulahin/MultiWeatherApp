package com.example.network.models.weather


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class HourNetworkModel(
    @SerializedName("chance_of_rain")
    @Expose
    val chanceOfRain: Int? = 0,
    @SerializedName("chance_of_snow")
    @Expose
    val chanceOfSnow: Int? = 0,
    @SerializedName("cloud")
    @Expose
    val cloud: Int? = 0,
    @SerializedName("condition")
    @Expose
    val condition: ConditionNetworkModel? = ConditionNetworkModel(),
    @SerializedName("dewpoint_c")
    @Expose
    val dewpointC: Double? = 0.0,
    @SerializedName("dewpoint_f")
    @Expose
    val dewpointF: Double? = 0.0,
    @SerializedName("feelslike_c")
    @Expose
    val feelslikeC: Double? = 0.0,
    @SerializedName("feelslike_f")
    @Expose
    val feelslikeF: Double? = 0.0,
    @SerializedName("gust_kph")
    @Expose
    val gustKph: Double? = 0.0,
    @SerializedName("gust_mph")
    @Expose
    val gustMph: Double? = 0.0,
    @SerializedName("heatindex_c")
    @Expose
    val heatindexC: Double? = 0.0,
    @SerializedName("heatindex_f")
    @Expose
    val heatindexF: Double? = 0.0,
    @SerializedName("humidity")
    @Expose
    val humidity: Int? = 0,
    @SerializedName("is_day")
    @Expose
    val isDay: Int? = 0,
    @SerializedName("precip_in")
    @Expose
    val precipIn: Double? = 0.0,
    @SerializedName("precip_mm")
    @Expose
    val precipMm: Double? = 0.0,
    @SerializedName("pressure_in")
    @Expose
    val pressureIn: Double? = 0.0,
    @SerializedName("pressure_mb")
    @Expose
    val pressureMb: Double? = 0.0,
    @SerializedName("temp_c")
    @Expose
    val tempC: Double? = 0.0,
    @SerializedName("temp_f")
    @Expose
    val tempF: Double? = 0.0,
    @SerializedName("time")
    @Expose
    val time: String? = "",
    @SerializedName("time_epoch")
    @Expose
    val timeEpoch: Int? = 0,
    @SerializedName("uv")
    @Expose
    val uv: Double? = 0.0,
    @SerializedName("vis_km")
    @Expose
    val visKm: Double? = 0.0,
    @SerializedName("vis_miles")
    @Expose
    val visMiles: Double? = 0.0,
    @SerializedName("will_it_rain")
    @Expose
    val willItRain: Int? = 0,
    @SerializedName("will_it_snow")
    @Expose
    val willItSnow: Int? = 0,
    @SerializedName("wind_degree")
    @Expose
    val windDegree: Int? = 0,
    @SerializedName("wind_dir")
    @Expose
    val windDir: String? = "",
    @SerializedName("wind_kph")
    @Expose
    val windKph: Double? = 0.0,
    @SerializedName("wind_mph")
    @Expose
    val windMph: Double? = 0.0,
    @SerializedName("windchill_c")
    @Expose
    val windchillC: Double? = 0.0,
    @SerializedName("windchill_f")
    @Expose
    val windchillF: Double? = 0.0
)