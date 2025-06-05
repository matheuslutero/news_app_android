package com.matheuslutero.newsapp.article.data.repository

import com.matheuslutero.newsapp.article.data.dto.toArticle
import com.matheuslutero.newsapp.article.data.network.ArticleRemoteService
import com.matheuslutero.newsapp.article.domain.model.Article
import com.matheuslutero.newsapp.article.domain.repository.ArticleRepository
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(
    private val service: ArticleRemoteService
) : ArticleRepository {
    override suspend fun getTopHeadlines(sources: String): Result<List<Article>> {
        return try {
            val response = service.getTopHeadlines(sources)
            val articles = response.articles
                .map { it.toArticle() }
                .sortedByDescending { it.publishedAt }
            Result.success(articles)
        } catch (t: Throwable) {
            Result.failure(t)
        }
    }
}
