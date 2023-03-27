package com.matheuslutero.newsapp.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matheuslutero.newsapp.core.model.Article
import com.matheuslutero.newsapp.core.model.Resource
import com.matheuslutero.newsapp.core.model.setLoading
import java.util.Date
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ArticlesViewModel : ViewModel() {

    private var _listData = MutableLiveData<Resource<List<Article>>>()
    val listData: LiveData<Resource<List<Article>>> = _listData

    init {
        fetchTopHeadLines()
    }

    fun fetchTopHeadLines() {
        viewModelScope.launch {
            _listData.setLoading()

            val articles = listOf(
                Article(
                    title = "Article 1",
                    description = "Description 1",
                    content = "content",
                    url = "https://www.google.com",
                    urlToImage = "https://picsum.photos/400",
                    publishedAt = Date(),
                ),
                Article(
                    title = "Article 2",
                    description = "Description 2",
                    content = "content",
                    url = "https://www.google.com",
                    urlToImage = "https://picsum.photos/400",
                    publishedAt = Date(),
                ),
            )

            viewModelScope.launch {
                delay(2000)
                _listData.value = Resource.success(articles)
            }
        }
    }
}
