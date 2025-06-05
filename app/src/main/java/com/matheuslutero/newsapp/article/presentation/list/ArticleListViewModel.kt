package com.matheuslutero.newsapp.article.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matheuslutero.newsapp.BuildConfig
import com.matheuslutero.newsapp.article.domain.repository.ArticleRepository
import com.matheuslutero.newsapp.core.domain.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleListViewModel @Inject constructor(
    private val repository: ArticleRepository
) : ViewModel() {

    private var _listData = MutableStateFlow(ArticleListState())
    val listData = _listData.asStateFlow()

    init {
        fetchTopHeadLines()
    }

    fun fetchTopHeadLines() {
        viewModelScope.launch {
            repository.getTopHeadlines(BuildConfig.SOURCES).onEach { resource ->
                when (resource) {
                    is Result.Success -> {
                        _listData.emit(ArticleListState(articles = resource.data ?: emptyList()))
                    }

                    is Result.Error -> {
                        _listData.emit(ArticleListState(isFailed = true))
                    }

                    is Result.Loading -> {
                        _listData.emit(ArticleListState(isLoading = true))
                    }
                }
            }.collect()
        }
    }
}