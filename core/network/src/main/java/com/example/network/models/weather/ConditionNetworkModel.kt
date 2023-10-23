package com.example.network.models.weather


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class ConditionNetworkModel(
    @SerializedName("code")
    @Expose
    val code: Int? = null,
    @SerializedName("icon")
    @Expose
    val icon: String? = null,
    @SerializedName("text")
    @Expose
    val text: String? = null
)