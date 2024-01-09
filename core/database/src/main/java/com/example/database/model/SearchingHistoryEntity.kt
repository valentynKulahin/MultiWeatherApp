package com.example.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search_history")
data class SearchingHistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val country: String,
    val city: String
)