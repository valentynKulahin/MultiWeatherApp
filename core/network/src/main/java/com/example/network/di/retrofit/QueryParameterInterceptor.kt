package com.example.network.di.retrofit

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response

class QueryParameterInterceptor(private val paramName: String, private val paramValue: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalHttpUrl = originalRequest.url

        val modifiedUrl: HttpUrl = originalHttpUrl.newBuilder()
            .addQueryParameter(paramName, paramValue)
            .build()

        val request = originalRequest.newBuilder()
            .url(modifiedUrl)
            .build()

        return chain.proceed(request)
    }
}