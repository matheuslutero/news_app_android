package com.matheuslutero.newsapp.article.data.network

import com.matheuslutero.newsapp.article.data.dto.ArticlesResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleRemoteService {
    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("sources") sources: String
    ): ArticlesResponseDto
}