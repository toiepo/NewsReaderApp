package com.example.newsreaderapp.ui_app.interfaces

import com.example.newsreaderapp.ui_app.model.News
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNews(): Flow<List<News>>
    suspend fun refreshNews()
}