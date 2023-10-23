package com.example.network.models.weather


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class AlertNetworkModel(
    @SerializedName("areas")
    @Expose
    val areas: String? = null,
    @SerializedName("category")
    @Expose
    val category: String? = null,
    @SerializedName("certainty")
    @Expose
    val certainty: String? = null,
    @SerializedName("desc")
    @Expose
    val desc: String? = null,
    @SerializedName("effective")
    @Expose
    val effective: String? = null,
    @SerializedName("event")
    @Expose
    val event: String? = null,
    @SerializedName("expires")
    @Expose
    val expires: String? = null,
    @SerializedName("headline")
    @Expose
    val headline: String? = null,
    @SerializedName("instruction")
    @Expose
    val instruction: String? = null,
    @SerializedName("msgtype")
    @Expose
    val msgtype: String? = null,
    @SerializedName("note")
    @Expose
    val note: String? = null,
    @SerializedName("severity")
    @Expose
    val severity: String? = null,
    @SerializedName("urgency")
    @Expose
    val urgency: String? = null
)