package com.matheuslutero.newsapp.core.network

import com.matheuslutero.newsapp.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.util.Date
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ApiServiceBuilder {
    private var baseUrl: String? = null
    private val interceptors: MutableList<Interceptor> = mutableListOf()

    fun baseUrl(baseUrl: String) = apply {
        this.baseUrl = baseUrl
    }

    fun addInterceptor(interceptor: Interceptor) = apply {
        interceptors += interceptor
    }

    inline fun <reified T> build(): T =
        build(T::class.java)

    fun <T> build(service: Class<T>): T {
        checkNotNull(baseUrl) { "Base URL required." }
        return createRetrofit()
            .create(service)
    }

    private fun createRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl!!)
            .client(createHttpClient())
            .addConverterFactory(createConverterFactory())
            .build()

    private fun createHttpClient(): OkHttpClient =
        OkHttpClient.Builder().apply {
            interceptors.forEach(::addInterceptor)
            if (BuildConfig.DEBUG) {
                addInterceptor(
                    HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BASIC)
                )
            }
        }.build()

    private fun createConverterFactory(): MoshiConverterFactory =
        MoshiConverterFactory.create(
            Moshi.Builder()
                .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
                .addLast(KotlinJsonAdapterFactory())
                .build()
        )
}
