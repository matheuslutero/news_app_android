package com.matheuslutero.newsapp.core.model

data class ArticlesResponse(
    val totalResults: Int,
    val articles: List<Article>
)
