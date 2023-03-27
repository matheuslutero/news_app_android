package com.matheuslutero.newsapp.core.repository

import com.matheuslutero.newsapp.core.model.Article
import com.matheuslutero.newsapp.core.model.Resource

interface NewsRepository {
    suspend fun getTopHeadlines(sources: String): Resource<List<Article>>
}
