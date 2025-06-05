package com.matheuslutero.newsapp.article.data.dto

import com.matheuslutero.newsapp.article.domain.model.Article
import com.matheuslutero.newsapp.core.util.DateSerializer
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

fun ArticleDto.toArticle(): Article {
    return Article(
        title = title,
        description = description,
        content = content,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
    )
}
