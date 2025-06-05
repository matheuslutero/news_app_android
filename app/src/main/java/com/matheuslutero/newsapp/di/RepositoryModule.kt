package com.matheuslutero.newsapp.di

import com.matheuslutero.newsapp.article.data.repository.ArticleRepositoryImpl
import com.matheuslutero.newsapp.article.domain.repository.ArticleRepository
import com.matheuslutero.newsapp.core.data.NewsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideNewsRepository(service: NewsApiService): ArticleRepository {
        return ArticleRepositoryImpl(service)
    }
}
