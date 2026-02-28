package com.example.newsreaderapp.ui_app.remote

data class GNewsArticleDto(
    val title: String,
    val description: String?,
    val image: String?,
    val url: String,
    val publishedAt: String,
)