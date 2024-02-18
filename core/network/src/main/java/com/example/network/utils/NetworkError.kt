package com.example.network.utils

import com.google.gson.annotations.SerializedName

data class NetworkError(
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String
)
