package com.example.newsreaderapp.ui_app.model

fun News.toUiModel(): NewsUiModel {
    return NewsUiModel(
        title = title,
        description = description,
        imageUrl = imageUrl,
        url = url,
        publishedAt = publishedAt,
        sourceName = sourceName
    )
}
