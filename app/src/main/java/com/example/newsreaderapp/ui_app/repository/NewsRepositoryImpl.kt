package com.example.newsreaderapp.ui_app.repository

import com.example.newsreaderapp.ui_app.interfaces.NewsRepository
import com.example.newsreaderapp.ui_app.model.News
import com.example.newsreaderapp.ui_app.remote.NewsRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepositoryImpl @Inject constructor(
    private val remoteDataSource: NewsRemoteDataSource,
) : NewsRepository {
    private val _newsState = MutableStateFlow<List<News>>(emptyList())
    override fun getNews(): Flow<List<News>> = _newsState

    override suspend fun refreshNews() {
        try {
            val latestNews = remoteDataSource.fetchNews()
            if (latestNews.isNotEmpty()) {
                _newsState.value = latestNews
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}