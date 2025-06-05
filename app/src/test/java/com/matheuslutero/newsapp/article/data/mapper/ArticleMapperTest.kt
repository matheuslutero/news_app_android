package com.matheuslutero.newsapp.article.data.mapper

import com.google.common.truth.Truth.assertThat
import com.matheuslutero.newsapp.article.data.dto.ArticleDto
import com.matheuslutero.newsapp.article.data.dto.toArticle
import org.junit.Test
import java.util.Date

class ArticleMapperTest {

    @Test
    fun `toArticle should correctly map all fields from ArticleDto to Article`() {
        // Given
        val articleDto = ArticleDto(
            title = "Test Title",
            description = "Test Description",
            content = "Test Content",
            urlToImage = "https://test-image.com",
            publishedAt = Date()
        )

        // When
        val article = articleDto.toArticle()

        // Then
        assertThat(article.title).isEqualTo(articleDto.title)
        assertThat(article.description).isEqualTo(articleDto.description)
        assertThat(article.content).isEqualTo(articleDto.content)
        assertThat(article.urlToImage).isEqualTo(articleDto.urlToImage)
        assertThat(article.publishedAt).isEqualTo(articleDto.publishedAt)
    }
}
