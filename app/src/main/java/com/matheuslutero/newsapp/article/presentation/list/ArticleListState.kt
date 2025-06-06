package com.matheuslutero.newsapp.article.presentation.list

import com.matheuslutero.newsapp.article.domain.model.Article

data class ArticleListState(
    val isLoading: Boolean = false,
    val isFailed: Boolean = false,
    val articles: List<Article> = emptyList(),
)