package com.matheuslutero.newsapp.article.data.dto

import com.matheuslutero.newsapp.article.domain.model.Article

data class ArticlesResponseDto(
    val totalResults: Int,
    val articles: List<Article>
)
