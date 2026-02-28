package com.example.newsreaderapp.ui_app.model

data class News(
    val title: String,
    val description: String,
    val imageUrl: String?,
    val url: String,
    val publishedAt: String,
    val sourceName: String,
)