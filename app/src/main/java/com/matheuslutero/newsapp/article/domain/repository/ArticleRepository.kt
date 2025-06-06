package com.matheuslutero.newsapp.article.domain.repository

import com.matheuslutero.newsapp.article.domain.model.Article

interface ArticleRepository {
    suspend fun getTopHeadlines(sources: String): Result<List<Article>>
}
