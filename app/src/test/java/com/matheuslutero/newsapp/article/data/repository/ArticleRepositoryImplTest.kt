package com.matheuslutero.newsapp.article.data.repository

import com.google.common.truth.Truth.assertThat
import com.matheuslutero.newsapp.article.data.dto.ArticleDto
import com.matheuslutero.newsapp.article.data.dto.ArticlesResponseDto
import com.matheuslutero.newsapp.article.data.network.ArticleRemoteService
import com.matheuslutero.newsapp.core.util.Result
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.exceptions.base.MockitoException
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.Date

@RunWith(MockitoJUnitRunner::class)
class ArticleRepositoryImplTest {

    @Mock
    private lateinit var mockArticleRemoteService: ArticleRemoteService

    private lateinit var repository: ArticleRepositoryImpl

    companion object {
        private const val TEST_SOURCES = "bbc-news"
    }

    @Before
    fun setUp() {
        repository = ArticleRepositoryImpl(mockArticleRemoteService)
    }

    @Test
    fun `getTopHeadlines should emit Loading first when called`() = runTest {
        // Given
        val articlesResponseDto = ArticlesResponseDto(
            totalResults = 0,
            articles = emptyList()
        )
        whenever(mockArticleRemoteService.getTopHeadlines(TEST_SOURCES))
            .thenReturn(articlesResponseDto)

        // When
        val results = repository.getTopHeadlines(TEST_SOURCES).toList()

        // Then
        assertThat(results).hasSize(2)
        assertThat(results[0]).isInstanceOf(Result.Loading::class.java)
        assertThat(results[1]).isInstanceOf(Result.Success::class.java)
    }

    @Test
    fun `getTopHeadlines should emit Success with sorted articles when succeeds`() = runTest {
        // Given
        val now = Instant.now()
        val firstArticle = createArticleDto(
            title = "First Article",
            publishedAt = Date.from(now.minus(1, ChronoUnit.HOURS))
        )
        val secondArticle = createArticleDto(
            title = "Second Article",
            publishedAt = Date.from(now.minus(2, ChronoUnit.HOURS))
        )
        val thirdArticle = createArticleDto(
            title = "Third Article",
            publishedAt = Date.from(now)
        )

        val articlesResponseDto = ArticlesResponseDto(
            totalResults = 3,
            articles = listOf(firstArticle, secondArticle, thirdArticle)
        )

        whenever(mockArticleRemoteService.getTopHeadlines(TEST_SOURCES))
            .thenReturn(articlesResponseDto)

        // When
        val result = repository.getTopHeadlines(TEST_SOURCES).toList().last()

        // Then
        assertThat(result).isInstanceOf(Result.Success::class.java)
        val successResult = result as Result.Success
        assertThat(successResult.data).isNotNull()
        assertThat(successResult.data).hasSize(3)

        // Verify articles are sorted by publishedAt in descending order (newest first)
        val sortedArticles = successResult.data!!
        assertThat(sortedArticles[0].title).isEqualTo("Third Article")
        assertThat(sortedArticles[1].title).isEqualTo("First Article")
        assertThat(sortedArticles[2].title).isEqualTo("Second Article")
    }

    @Test
    fun `getTopHeadlines should emit Error when exception occurs`() = runTest {
        // Given
        whenever(mockArticleRemoteService.getTopHeadlines(TEST_SOURCES))
            .thenThrow(MockitoException("Network error"))

        // When
        val result = repository.getTopHeadlines(TEST_SOURCES).toList().last()

        // Then
        assertThat(result).isInstanceOf(Result.Error::class.java)
        val errorResult = result as Result.Error
        assertThat(errorResult.message).isNotNull()
        assertThat(errorResult.data).isNull()
    }

    private fun createArticleDto(
        title: String = "Default Title",
        description: String = "Default Description",
        content: String = "Default Content",
        urlToImage: String = "https://default-image.com",
        publishedAt: Date? = Date()
    ): ArticleDto {
        return ArticleDto(
            title = title,
            description = description,
            content = content,
            urlToImage = urlToImage,
            publishedAt = publishedAt
        )
    }
}
