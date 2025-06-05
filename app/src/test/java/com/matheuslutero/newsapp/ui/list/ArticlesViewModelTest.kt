package com.matheuslutero.newsapp.ui.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.google.common.truth.Truth
import com.matheuslutero.newsapp.core.model.Article
import com.matheuslutero.newsapp.core.model.Resource
import com.matheuslutero.newsapp.core.repository.NewsRepository
import com.matheuslutero.newsapp.ui.list.viewmodel.ArticlesViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.inOrder
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ArticlesViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val dispatcher = StandardTestDispatcher()

    @Mock
    lateinit var mockRepository: NewsRepository

    private lateinit var sut: ArticlesViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        sut = ArticlesViewModel(mockRepository)
    }

    @Test
    fun testFetchTopHeadlines_success() = runTest {
        val articles = listOf(Article(), Article())
        whenever(mockRepository.getTopHeadlines(any()))
            .thenReturn(Resource.success(articles))

        sut.fetchTopHeadLines()
        dispatcher.scheduler.advanceUntilIdle()

        val listData = sut.listData.value
        Truth.assertThat(listData?.status).isEqualTo(Resource.Status.Success)
        Truth.assertThat(listData?.data).isEqualTo(articles)
        Truth.assertThat(listData?.error).isNull()
    }

    @Test
    fun testFetchTopHeadlines_failure() = runTest {
        val exception = Exception("Test exception")
        whenever(mockRepository.getTopHeadlines(any()))
            .thenReturn(Resource.failure(exception))

        sut.fetchTopHeadLines()
        dispatcher.scheduler.advanceUntilIdle()

        val listData = sut.listData.value
        Truth.assertThat(listData?.status).isEqualTo(Resource.Status.Failure)
        Truth.assertThat(listData?.data).isNull()
        Truth.assertThat(listData?.error).isEqualTo(exception)
    }

    @Test
    fun testFetchTopHeadlines_emitsLoadingBeforeSuccess() = runTest {
        val observer = mock<Observer<Resource<List<Article>>>>()
        whenever(mockRepository.getTopHeadlines(any()))
            .thenReturn(Resource.success(emptyList()))

        sut.listData.observeForever(observer)
        sut.fetchTopHeadLines()
        dispatcher.scheduler.advanceUntilIdle()

        inOrder(observer) {
            verify(observer).onChanged(Resource.loading())
            verify(observer).onChanged(Resource.success(emptyList()))
        }
    }
}
