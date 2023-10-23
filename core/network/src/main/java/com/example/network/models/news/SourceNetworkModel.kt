package com.example.network.models.news


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class SourceNetworkModel(
    @SerializedName("id")
    @Expose
    val id: String? = null,
    @SerializedName("name")
    @Expose
    val name: String? = null
)