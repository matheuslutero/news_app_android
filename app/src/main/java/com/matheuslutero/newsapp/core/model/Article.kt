package com.matheuslutero.newsapp.core.model

import java.util.Date

data class Article(
    val title: String? = null,
    val description: String? = null,
    val content: String? = null,
    val url: String? = null,
    val urlToImage: String? = null,
    val publishedAt: Date? = null,
)
