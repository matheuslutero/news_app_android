package com.matheuslutero.newsapp.ui.detail

import com.google.common.truth.Truth
import com.matheuslutero.newsapp.core.model.Article
import com.matheuslutero.newsapp.ui.detail.viewmodel.ArticleDetailViewModel
import java.util.Date
import org.junit.Before
import org.junit.Test

class ArticleDetailViewModelTest {

    private lateinit var article: Article
    private lateinit var sut: ArticleDetailViewModel

    @Before
    fun setup() {
        article = Article(
            title = "Test Article",
            description = "This is a test article description [+25 chars]",
            content = "This is a test article content [+100 chars]",
            url = "https://test-article.com",
            urlToImage = "https://test-image.com",
            publishedAt = Date()
        )
        sut = ArticleDetailViewModel()
        sut.init(article)
    }

    @Test
    fun testTitle_getter() {
        Truth.assertThat(sut.title).isEqualTo(article.title)
    }

    @Test
    fun testUrlToImage_getter() {
        Truth.assertThat(sut.urlToImage).isEqualTo(article.urlToImage)
    }

    @Test
    fun testPublishedAt_getter() {
        Truth.assertThat(sut.publishedAt).isEqualTo(article.publishedAt)
    }

    @Test
    fun testDescription_getter() {
        val expected = article.description?.replace("[+25 chars]", "")
        Truth.assertThat(sut.description).isEqualTo(expected)
    }

    @Test
    fun testContent_getter() {
        val expected = article.content?.replace("[+100 chars]", "")
        Truth.assertThat(sut.content).isEqualTo(expected)
    }
}
