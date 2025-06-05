package com.matheuslutero.newsapp.ui.list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matheuslutero.newsapp.BuildConfig
import com.matheuslutero.newsapp.core.model.Resource
import com.matheuslutero.newsapp.core.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticlesViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    private var _listData = MutableStateFlow(ArticlesViewState())
    val listData = _listData.asStateFlow()

    init {
        fetchTopHeadLines()
    }

    fun fetchTopHeadLines() {
        viewModelScope.launch {
            repository.getTopHeadlines(BuildConfig.SOURCES).onEach { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _listData.emit(ArticlesViewState(articles = resource.data ?: emptyList()))
                    }

                    is Resource.Error -> {
                        _listData.emit(ArticlesViewState(isFailed = true))
                    }

                    is Resource.Loading -> {
                        _listData.emit(ArticlesViewState(isLoading = true))
                    }
                }
            }.collect()
        }
    }
}
