package com.matheuslutero.newsapp.navigation

import com.matheuslutero.newsapp.article.domain.model.Article
import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    object Articles : Route

    @Serializable
    data class ArticleDetail(val article: Article) : Route
}
