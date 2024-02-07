package com.example.network.models.country

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CountryItemNetworkModel(
    @SerializedName("country")
    @Expose
    val country: String? = null,
    @SerializedName("id")
    @Expose
    val id: Int? = null,
    @SerializedName("lat")
    @Expose
    val lat: Double? = null,
    @SerializedName("lon")
    @Expose
    val lon: Double? = null,
    @SerializedName("name")
    @Expose
    val name: String? = null,
    @SerializedName("region")
    @Expose
    val region: String? = null,
    @SerializedName("url")
    @Expose
    val url: String? = null
)