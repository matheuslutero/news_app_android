package com.matheuslutero.newsapp.article.domain.model

import com.matheuslutero.newsapp.article.domain.DateSerializer
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable()
data class Article(
    val title: String? = null,
    val description: String? = null,
    val content: String? = null,
    val urlToImage: String? = null,
    @Serializable(with = DateSerializer::class)
    val publishedAt: Date? = null,
)
