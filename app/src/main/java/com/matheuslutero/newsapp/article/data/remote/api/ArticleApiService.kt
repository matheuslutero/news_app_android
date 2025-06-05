package com.matheuslutero.newsapp.article.data.remote.api

import com.matheuslutero.newsapp.article.data.remote.dto.ArticlesResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleApiService {
    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("sources") sources: String
    ): ArticlesResponseDto
}