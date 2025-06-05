package com.matheuslutero.newsapp.ui.app

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    object Articles

    @Serializable
    object ArticleDetail
}
