package com.example.network.repos

import com.example.common.ApiResult
import com.example.network.util.ApiHandler
import retrofit2.Call

suspend fun execute(request: () -> Call<*>): ApiResult {
    return when (val response = ApiHandler.handleApi { request.invoke().execute() }) {
        is ApiResult.ApiSuccess -> ApiResult.ApiSuccess(data = response.data)
        is ApiResult.ApiError -> ApiResult.ApiError(
            code = response.code,
            message = response.message
        )
        is ApiResult.ApiException -> ApiResult.ApiException(e = response.e)
    }
}