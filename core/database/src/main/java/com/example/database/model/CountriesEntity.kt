package com.example.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourites_countries")
data class CountriesEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val country: String,
    val city: String
)
