package com.example.newsreaderapp.ui_app.repository

import com.example.newsreaderapp.ui_app.interfaces.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindNewsRepository(
        impl: NewsRepositoryImpl,
    ): NewsRepository
}