package com.matheuslutero.newsapp.core.data.dto

data class ArticlesResponseDto(
    val totalResults: Int,
    val articles: List<ArticleDto>
)
