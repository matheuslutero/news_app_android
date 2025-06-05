package com.matheuslutero.newsapp.article.data.repository

import com.matheuslutero.newsapp.article.data.mapper.toArticle
import com.matheuslutero.newsapp.article.domain.model.Article
import com.matheuslutero.newsapp.article.domain.repository.ArticleRepository
import com.matheuslutero.newsapp.core.data.network.NewsApiService
import com.matheuslutero.newsapp.core.domain.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(
    private val service: NewsApiService
) : ArticleRepository {
    override suspend fun getTopHeadlines(sources: String): Flow<Result<List<Article>>> = flow {
        try {
            emit(Result.Loading())
            val response = service.getTopHeadlines(sources)
            val articles = response.articles
                .map { it.toArticle() }
                .sortedByDescending { it.publishedAt }
            emit(Result.Success(articles))
        } catch (e: HttpException) {
            emit(Result.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (_: IOException) {
            emit(Result.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}
