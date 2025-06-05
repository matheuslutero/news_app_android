package com.matheuslutero.newsapp.article.data.mapper

import com.matheuslutero.newsapp.article.domain.model.Article
import com.matheuslutero.newsapp.core.data.dto.ArticleDto

fun ArticleDto.toArticle(): Article {
    return Article(
        title = title,
        description = description,
        content = content,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
    )
}
