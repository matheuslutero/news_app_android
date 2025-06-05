package com.matheuslutero.newsapp.article.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matheuslutero.newsapp.BuildConfig
import com.matheuslutero.newsapp.article.domain.repository.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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
            _listData.emit(ArticleListState(isLoading = true))
            repository.getTopHeadlines(BuildConfig.SOURCES).onSuccess {
                _listData.emit(ArticleListState(articles = it))
            }.onFailure {
                _listData.emit(ArticleListState(isFailed = true))
            }
        }
    }
}