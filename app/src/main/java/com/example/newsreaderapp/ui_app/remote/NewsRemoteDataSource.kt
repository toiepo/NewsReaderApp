package com.example.newsreaderapp.ui_app.remote

import android.util.Log
import com.example.newsreaderapp.BuildConfig
import com.example.newsreaderapp.ui_app.model.News
import javax.inject.Inject

class NewsRemoteDataSource @Inject constructor(
    private val api: GNewsApi,
) {
    suspend fun fetchNews(): List<News> {
        return try {
            Log.d("API_CALL", "Fetching news...")

            val response = api.getTopHeadlines(
                category = "general", apiKey = BuildConfig.NEWS_API_KEY
            )

            val newsList = response.articles.map { article ->
                News(
                    title = article.title ?: "No Title",
                    description = article.description ?: "No Description",
                    imageUrl = article.image,
                    url = article.url ?: "",
                    publishedAt = article.publishedAt ?: "",
                    sourceName = article.source?.name ?: "Unknown"
                )
            }

            Log.d("API_SUCCESS", "Loaded ${newsList.size} articles")
            newsList

        } catch (e: retrofit2.HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            Log.e("API_ERROR", "HTTP Error ${e.code()}: $errorBody")
            emptyList()
        } catch (e: Exception) {
            Log.e("API_ERROR", "Unknown Error: ${e.message}")
            emptyList()
        }
    }
}