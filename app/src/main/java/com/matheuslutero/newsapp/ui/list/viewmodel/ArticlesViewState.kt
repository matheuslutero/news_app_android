package com.matheuslutero.newsapp.ui.list.viewmodel

import com.matheuslutero.newsapp.core.model.Article

data class ArticlesViewState(
    val isLoading: Boolean = false,
    val isFailed: Boolean = false,
    val articles: List<Article> = emptyList(),
)
