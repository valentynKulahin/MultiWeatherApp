package com.example.common.network.response

sealed class DataResponse<out T : Any, out U : Any> {
    data class Success<T : Any>(val body: T) : DataResponse<T, Nothing>()
    data class Error<U : Any>(val error: DataResponseError) : DataResponse<Nothing, U>()
}