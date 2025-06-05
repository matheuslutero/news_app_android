package com.matheuslutero.newsapp.article.data.mapper

import com.google.common.truth.Truth.assertThat
import com.matheuslutero.newsapp.article.data.remote.dto.ArticleDto
import com.matheuslutero.newsapp.article.data.remote.dto.toArticle
import org.junit.Test
import java.util.Date

class ArticleMapperTest {

    @Test
    fun `toArticle should correctly map all fields from ArticleDto to Article`() {
        // Given
        val testDate = Date()
        val articleDto = ArticleDto(
            title = "Test Title",
            description = "Test Description",
            content = "Test Content",
            urlToImage = "https://test-image.com",
            publishedAt = testDate
        )

        // When
        val article = articleDto.toArticle()

        // Then
        assertThat(article.title).isEqualTo("Test Title")
        assertThat(article.description).isEqualTo("Test Description")
        assertThat(article.content).isEqualTo("Test Content")
        assertThat(article.urlToImage).isEqualTo("https://test-image.com")
        assertThat(article.publishedAt).isEqualTo(testDate)
    }
}
