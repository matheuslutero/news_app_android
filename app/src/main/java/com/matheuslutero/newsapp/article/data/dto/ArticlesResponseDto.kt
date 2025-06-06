package com.matheuslutero.newsapp.article.data.dto

data class ArticlesResponseDto(
    val totalResults: Int,
    val articles: List<ArticleDto>
)
