package com.example.database.util

sealed class DatabaseResponse<out T : Any, out U : Any> {
    data class Success<T : Any>(val body: T) : DatabaseResponse<T, Nothing>()
    data class Error<U : Any>(val error: DatabaseResponseError) : DatabaseResponse<Nothing, U>()
}