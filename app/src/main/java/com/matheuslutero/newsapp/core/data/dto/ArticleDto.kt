package com.matheuslutero.newsapp.core.data.dto

import com.matheuslutero.newsapp.article.domain.model.DateSerializer
import kotlinx.serialization.Serializable
import java.util.Date

data class ArticleDto(
    val title: String? = null,
    val description: String? = null,
    val content: String? = null,
    val urlToImage: String? = null,
    @Serializable(with = DateSerializer::class)
    val publishedAt: Date? = null,
)
