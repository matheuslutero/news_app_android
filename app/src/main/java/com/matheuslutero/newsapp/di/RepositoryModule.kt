package com.matheuslutero.newsapp.di

import com.matheuslutero.newsapp.article.data.repository.ArticleRepositoryImpl
import com.matheuslutero.newsapp.article.domain.repository.ArticleRepository
import com.matheuslutero.newsapp.article.data.remote.api.ArticleApiService
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
    fun provideNewsRepository(service: ArticleApiService): ArticleRepository {
        return ArticleRepositoryImpl(service)
    }
}
