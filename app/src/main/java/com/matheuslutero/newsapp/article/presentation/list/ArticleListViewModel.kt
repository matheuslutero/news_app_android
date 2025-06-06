package com.matheuslutero.newsapp.article.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matheuslutero.newsapp.BuildConfig
import com.matheuslutero.newsapp.article.domain.repository.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleListViewModel @Inject constructor(
    private val repository: ArticleRepository
) : ViewModel() {

    private var _stateFlow = MutableStateFlow(ArticleListState())
    val stateFlow = _stateFlow
        .onStart { fetchTopHeadLines() }
        .stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(60000L),
            initialValue = ArticleListState()
        )

    fun fetchTopHeadLines() {
        viewModelScope.launch {
            _stateFlow.emit(ArticleListState(isLoading = true))
            repository.getTopHeadlines(BuildConfig.SOURCES).onSuccess {
                _stateFlow.emit(ArticleListState(articles = it))
            }.onFailure {
                _stateFlow.emit(ArticleListState(isFailed = true))
            }
        }
    }
}