package com.matheuslutero.newsapp.article.data.repository

import com.matheuslutero.newsapp.article.data.remote.api.ArticleApiService
import com.matheuslutero.newsapp.article.data.remote.dto.toArticle
import com.matheuslutero.newsapp.article.domain.model.Article
import com.matheuslutero.newsapp.article.domain.repository.ArticleRepository
import com.matheuslutero.newsapp.core.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(
    private val service: ArticleApiService
) : ArticleRepository {
    override suspend fun getTopHeadlines(sources: String): Flow<Result<List<Article>>> = flow {
        try {
            emit(Result.Loading())
            val response = service.getTopHeadlines(sources)
            val articles = response.articles
                .map { it.toArticle() }
                .sortedByDescending { it.publishedAt }
            emit(Result.Success(articles))
        } catch (t: Throwable) {
            emit(Result.Error("An unexpected error occured: ${t.message}"))
        }
    }
}
