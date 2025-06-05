package com.matheuslutero.newsapp.core.repository

import com.matheuslutero.newsapp.core.model.Article
import com.matheuslutero.newsapp.core.model.Resource
import com.matheuslutero.newsapp.core.network.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val service: ApiService
) : NewsRepository {

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
