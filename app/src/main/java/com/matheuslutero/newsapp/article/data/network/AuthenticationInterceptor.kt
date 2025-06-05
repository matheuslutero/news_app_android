package com.matheuslutero.newsapp.article.data.network

import okhttp3.Interceptor
import okhttp3.Response

class AuthenticationInterceptor(
    private val apiKey: String
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url

        val changedUrl = originalUrl.newBuilder()
            .addQueryParameter("apiKey", apiKey)
            .build()

        val changedRequest = originalRequest.newBuilder()
            .url(changedUrl)
            .build()

        return chain.proceed(changedRequest)
    }
}