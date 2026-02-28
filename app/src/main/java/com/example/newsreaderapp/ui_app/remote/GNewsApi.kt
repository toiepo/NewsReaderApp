package com.example.newsreaderapp.ui_app.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface GNewsApi {
    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("category") category: String,
        @Query("lang") lang: String = "en",
        @Query("apikey") apiKey: String,
    ): NewsResponse
}