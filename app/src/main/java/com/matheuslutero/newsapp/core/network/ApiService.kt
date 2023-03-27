package com.matheuslutero.newsapp.core.network

import com.matheuslutero.newsapp.core.model.ArticlesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("sources") sources: String
    ): ArticlesResponse
}
