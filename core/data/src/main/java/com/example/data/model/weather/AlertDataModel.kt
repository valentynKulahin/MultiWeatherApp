package com.example.data.model.weather

data class AlertDataModel(
    val areas: String? = null,
    val category: String? = null,
    val certainty: String? = null,
    val desc: String? = null,
    val effective: String? = null,
    val event: String? = null,
    val expires: String? = null,
    val headline: String? = null,
    val instruction: String? = null,
    val msgtype: String? = null,
    val note: String? = null,
    val severity: String? = null,
    val urgency: String? = null
)