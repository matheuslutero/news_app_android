package com.matheuslutero.newsapp.ui.detail.viewmodel

import androidx.lifecycle.ViewModel
import com.matheuslutero.newsapp.core.model.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class ArticleDetailViewModel @Inject constructor() : ViewModel() {

    private var article: Article? = null

    fun init(article: Article) {
        this.article = article
    }

    val title: String?
        get() = article?.title

    val urlToImage: String?
        get() = article?.urlToImage

    val publishedAt: Date?
        get() = article?.publishedAt

    val description: String?
        get() = article?.description?.sanitize()

    val content: String?
        get() = article?.content?.sanitize()

    private fun String.sanitize() = replace(Regex("\\[\\+\\d+ chars]"), "")
}
