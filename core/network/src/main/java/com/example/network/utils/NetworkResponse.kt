package com.example.network.utils

sealed class NetworkResponse<out T : Any, out U : Any> {

    data class ApiSuccess<T : Any>(val body: T) : NetworkResponse<T, Nothing>()
    data class ApiError<U : Any>(val code: Int, val message: U) : NetworkResponse<Nothing, U>()
    data class UnknownError(val error: Throwable?) : NetworkResponse<Nothing, Nothing>()

}