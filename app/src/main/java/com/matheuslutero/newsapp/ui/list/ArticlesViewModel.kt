package com.matheuslutero.newsapp.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matheuslutero.newsapp.BuildConfig
import com.matheuslutero.newsapp.core.model.Article
import com.matheuslutero.newsapp.core.model.Resource
import com.matheuslutero.newsapp.core.model.setLoading
import com.matheuslutero.newsapp.core.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class ArticlesViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    private var _listData = MutableLiveData<Resource<List<Article>>>()
    val listData: LiveData<Resource<List<Article>>> = _listData

    init {
        fetchTopHeadLines()
    }

    fun fetchTopHeadLines() {
        viewModelScope.launch {
            _listData.setLoading()
            _listData.value = repository.getTopHeadlines(
                BuildConfig.SOURCES
            )
        }
    }
}
