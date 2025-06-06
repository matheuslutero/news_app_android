package com.matheuslutero.newsapp.article.data.repository

import com.google.common.truth.Truth.assertThat
import com.matheuslutero.newsapp.article.data.dto.ArticleDto
import com.matheuslutero.newsapp.article.data.dto.ArticlesResponseDto
import com.matheuslutero.newsapp.article.data.dto.toArticle
import com.matheuslutero.newsapp.article.data.network.ArticleRemoteService
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.Date

class ArticleRepositoryImplTest {

    // Constants
    companion object {
        private const val NEWS_SOURCE = "test-source"
        private const val ERROR_MESSAGE = "Unexpected error"
    }

    // Test subject & dependencies
    private lateinit var repository: ArticleRepositoryImpl
    private lateinit var mockService: ArticleRemoteService

    // Setup
    @Before
    fun setUp() {
        mockService = mockk()
        repository = ArticleRepositoryImpl(mockService)
    }

    // Tests
    @Test
    fun `getTopHeadlines returns articles sorted by date descending`() = runTest {
        // Given
        val now = Instant.now()

        val firstArticle = ArticleDto(publishedAt = Date.from(now.minus(1, ChronoUnit.HOURS)))
        val secondArticle = ArticleDto(publishedAt = Date.from(now.minus(2, ChronoUnit.HOURS)))
        val latestArticle = ArticleDto(publishedAt = Date.from(now))

        val response = ArticlesResponseDto(
            totalResults = 3,
            articles = listOf(firstArticle, secondArticle, latestArticle)
        )

        coEvery { mockService.getTopHeadlines(NEWS_SOURCE) } returns response

        // When
        val result = repository.getTopHeadlines(NEWS_SOURCE)

        // Then
        coVerify { mockService.getTopHeadlines(NEWS_SOURCE) }

        assertThat(result.isSuccess).isTrue()

        val articles = result.getOrThrow()
        assertThat(articles).hasSize(3)

        // Verify articles are correctly sorted by publishedAt (newest first)
        assertThat(articles[0]).isEqualTo(latestArticle.toArticle())
        assertThat(articles[1]).isEqualTo(firstArticle.toArticle())
        assertThat(articles[2]).isEqualTo(secondArticle.toArticle())
    }

    @Test
    fun `getTopHeadlines returns failure when service throws exception`() = runTest {
        // Given
        val exception = RuntimeException(ERROR_MESSAGE)
        coEvery { mockService.getTopHeadlines(NEWS_SOURCE) } throws exception

        // When
        val result = repository.getTopHeadlines(NEWS_SOURCE)

        // Then
        coVerify { mockService.getTopHeadlines(NEWS_SOURCE) }

        assertThat(result.isFailure).isTrue()
        assertThat(result.exceptionOrNull()).isSameInstanceAs(exception)
    }
}
