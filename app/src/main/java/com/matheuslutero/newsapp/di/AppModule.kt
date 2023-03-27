package com.matheuslutero.newsapp.di

import android.content.Context
import coil.ImageLoader
import coil.request.CachePolicy
import com.matheuslutero.newsapp.core.repository.NewsRepository
import com.matheuslutero.newsapp.core.repository.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRepository(): NewsRepository = NewsRepositoryImpl()

    @Singleton
    @Provides
    fun provideImageLoader(@ApplicationContext context: Context): ImageLoader =
        ImageLoader.Builder(context)
            .diskCachePolicy(CachePolicy.ENABLED)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .build()
}
