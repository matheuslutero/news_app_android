package com.matheuslutero.newsapp.core.repository

import com.google.common.truth.Truth
import com.matheuslutero.newsapp.BuildConfig
import com.matheuslutero.newsapp.article.domain.model.Article
import com.matheuslutero.newsapp.article.data.dto.ArticlesResponseDto
import com.matheuslutero.newsapp.core.domain.Resource
import com.matheuslutero.newsapp.core.data.NewsApiService
import com.matheuslutero.newsapp.article.data.repository.ArticleRepositoryImpl
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.Date
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.exceptions.base.MockitoException
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class NewsRepositoryImplTest {

    @Mock
    lateinit var mockService: NewsApiService

    @InjectMocks
    lateinit var sut: ArticleRepositoryImpl

    @Test
    fun testGetTopHeadlines_success() = runBlocking {
        val now = Instant.now()
        val unsortedArticles = listOf(
            Article(publishedAt = Date.from(now.minus(1, ChronoUnit.HOURS))),
            Article(publishedAt = Date.from(now.minus(2, ChronoUnit.HOURS))),
            Article(publishedAt = Date.from(now))
        )
        val sortedArticles = unsortedArticles.sortedByDescending { it.publishedAt }
        val response = ArticlesResponseDto(unsortedArticles.size, unsortedArticles)
        whenever(mockService.getTopHeadlines(any())).thenReturn(response)

        val result = sut.getTopHeadlines(BuildConfig.SOURCES)

        Truth.assertThat(result).isEqualTo(Resource.success(sortedArticles))
    }

    @Test
    fun testGetTopHeadlines_failure() = runBlocking {
        val exception = MockitoException("Error")
        whenever(mockService.getTopHeadlines(any())).thenThrow(exception)

        val result = sut.getTopHeadlines(BuildConfig.SOURCES)

        Truth.assertThat(result.status).isEqualTo(Resource.Status.Failure)
        Truth.assertThat(result.error).isEqualTo(exception)
    }
}
