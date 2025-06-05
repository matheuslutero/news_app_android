package com.matheuslutero.newsapp.article.presentation.list

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.matheuslutero.newsapp.BuildConfig
import com.matheuslutero.newsapp.article.domain.model.Article
import com.matheuslutero.newsapp.article.domain.repository.ArticleRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.util.Date

@OptIn(ExperimentalCoroutinesApi::class)
class ArticleListViewModelTest {

    // Constants
    companion object {
        private const val ERROR_MESSAGE = "Unexpected error"
    }

    // Test subject & dependencies
    private lateinit var viewModel: ArticleListViewModel
    private val mockRepository: ArticleRepository = mockk()
    private lateinit var testDispatcher: TestDispatcher

    // Setup
    @Before
    fun setUp() {
        testDispatcher = StandardTestDispatcher()
        Dispatchers.setMain(testDispatcher)
        viewModel = ArticleListViewModel(mockRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    // Tests
    @Test
    fun `should emit loading then success states when fetching articles`() = runTest {
        // Given
        val expectedArticles = listOf(
            Article(title = "Article 1", publishedAt = Date()),
            Article(title = "Article 2", publishedAt = Date())
        )
        coEvery { mockRepository.getTopHeadlines(BuildConfig.SOURCES) } coAnswers {
            delay(100) // Add delay to separate loading and success emissions
            Result.success(expectedArticles)
        }

        viewModel.stateFlow.test {
            // First emission should be the initial empty state
            val initialState = awaitItem()
            assertThat(initialState.isLoading).isFalse()
            assertThat(initialState.articles).isEmpty()
            assertThat(initialState.isFailed).isFalse()

            // Second emission should be loading state
            val loadingState = awaitItem()
            assertThat(loadingState.isLoading).isTrue()
            assertThat(loadingState.articles).isEmpty()
            assertThat(loadingState.isFailed).isFalse()

            // Third emission should be success state with articles
            val successState = awaitItem()
            assertThat(successState.isLoading).isFalse()
            assertThat(successState.articles).isEqualTo(expectedArticles)
            assertThat(successState.isFailed).isFalse()

            coVerify { mockRepository.getTopHeadlines(BuildConfig.SOURCES) }
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `should emit loading then error states when fetching fails`() = runTest {
        // Given
        val exception = RuntimeException(ERROR_MESSAGE)
        coEvery { mockRepository.getTopHeadlines(BuildConfig.SOURCES) } coAnswers {
            delay(100) // Add delay to separate loading and error emissions
            Result.failure(exception)
        }

        viewModel.stateFlow.test {
            // First emission should be the initial empty state
            val initialState = awaitItem()
            assertThat(initialState.isLoading).isFalse()
            assertThat(initialState.articles).isEmpty()
            assertThat(initialState.isFailed).isFalse()

            // Second emission should be loading state
            val loadingState = awaitItem()
            assertThat(loadingState.isLoading).isTrue()
            assertThat(loadingState.articles).isEmpty()
            assertThat(loadingState.isFailed).isFalse()

            // Third emission should be error state
            val errorState = awaitItem()
            assertThat(errorState.isLoading).isFalse()
            assertThat(errorState.articles).isEmpty()
            assertThat(errorState.isFailed).isTrue()

            coVerify { mockRepository.getTopHeadlines(BuildConfig.SOURCES) }
            cancelAndConsumeRemainingEvents()
        }
    }
}
