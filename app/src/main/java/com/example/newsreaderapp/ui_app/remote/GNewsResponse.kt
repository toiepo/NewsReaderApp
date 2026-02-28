package com.example.newsreaderapp.ui_app.remote

data class NewsResponse(
    val articles: List<ArticleRemote>,
)

data class ArticleRemote(
    val title: String?,
    val description: String?,
    val image: String?,
    val url: String?,
    val publishedAt: String?,
    val source: SourceRemote?,
)

data class SourceRemote(val name: String?)
