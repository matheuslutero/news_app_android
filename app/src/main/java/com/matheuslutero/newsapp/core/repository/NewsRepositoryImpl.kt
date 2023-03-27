package com.matheuslutero.newsapp.core.repository

import com.matheuslutero.newsapp.core.model.Article
import com.matheuslutero.newsapp.core.model.Resource
import java.util.Date
import kotlinx.coroutines.delay

class NewsRepositoryImpl : NewsRepository {

    override suspend fun getTopHeadlines(sources: String): Resource<List<Article>> {
        return try {
            delay(2000)
            val articles = listOf(
                Article(
                    title = "Article 1",
                    description = "Description 1",
                    content = "content",
                    url = "https://www.google.com",
                    urlToImage = "https://picsum.photos/400",
                    publishedAt = Date(),
                ),
                Article(
                    title = "Article 2",
                    description = "Description 2",
                    content = "content",
                    url = "https://www.google.com",
                    urlToImage = "https://picsum.photos/400",
                    publishedAt = Date(),
                ),
            )

            Resource.success(articles)
        } catch (t: Throwable) {
            Resource.failure(t)
        }
    }
}
