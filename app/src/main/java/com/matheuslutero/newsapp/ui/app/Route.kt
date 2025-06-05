package com.matheuslutero.newsapp.ui.app

import com.matheuslutero.newsapp.core.model.Article
import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    object Articles: Route

    @Serializable
    data class ArticleDetail(val article: Article): Route
}
