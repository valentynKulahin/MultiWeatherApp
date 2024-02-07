package com.example.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourites_countries")
data class CountryItemDatabaseModel(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val country: String? = null,
    val lat: Double? = null,
    val lon: Double? = null,
    val name: String? = null,
    val region: String? = null,
    val url: String? = null
)