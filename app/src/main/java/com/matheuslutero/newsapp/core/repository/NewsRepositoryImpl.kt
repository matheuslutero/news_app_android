package com.matheuslutero.newsapp.core.repository

import com.matheuslutero.newsapp.core.model.Article
import com.matheuslutero.newsapp.core.model.Resource
import com.matheuslutero.newsapp.core.network.ApiService
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val service: ApiService
) : NewsRepository {

    override suspend fun getTopHeadlines(sources: String): Resource<List<Article>> {
        return try {
            val response = service.getTopHeadlines(sources)
            val articles = response.articles.sortedByDescending { it.publishedAt }
            Resource.success(articles)
        } catch (t: Throwable) {
            Resource.failure(t)
        }
    }
}
