package com.matheuslutero.newsapp.core.data.network

import com.matheuslutero.newsapp.core.data.dto.ArticlesResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("sources") sources: String
    ): ArticlesResponseDto
}