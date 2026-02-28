package com.example.newsreaderapp.ui_app.state

import com.example.newsreaderapp.ui_app.model.NewsUiModel

// ui/state/NewsUiState.kt
data class NewsUiState(
    val isLoading: Boolean = false,
    val news: List<NewsUiModel> = emptyList(),
    val error: String? = null,
)