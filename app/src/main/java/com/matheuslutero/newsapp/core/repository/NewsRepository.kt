package com.matheuslutero.newsapp.core.repository

import com.matheuslutero.newsapp.core.model.Article
import com.matheuslutero.newsapp.core.model.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getTopHeadlines(sources: String): Flow<Resource<List<Article>>>
}
