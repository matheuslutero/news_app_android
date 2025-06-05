package com.matheuslutero.newsapp.article.domain.repository

import com.matheuslutero.newsapp.article.domain.model.Article
import com.matheuslutero.newsapp.core.domain.Resource
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {
    suspend fun getTopHeadlines(sources: String): Flow<Resource<List<Article>>>
}
