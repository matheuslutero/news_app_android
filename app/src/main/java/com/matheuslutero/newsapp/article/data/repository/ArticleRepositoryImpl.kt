package com.matheuslutero.newsapp.article.data.repository

import com.matheuslutero.newsapp.article.domain.model.Article
import com.matheuslutero.newsapp.article.domain.repository.ArticleRepository
import com.matheuslutero.newsapp.core.data.NewsApiService
import com.matheuslutero.newsapp.core.domain.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(
    private val service: NewsApiService
) : ArticleRepository {
    override suspend fun getTopHeadlines(sources: String): Flow<Resource<List<Article>>> = flow {
        try {
            emit(Resource.Loading())
            val response = service.getTopHeadlines(sources)
            val articles = response.articles.sortedByDescending { it.publishedAt }
            emit(Resource.Success(articles))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (_: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}
