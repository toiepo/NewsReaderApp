package com.example.newsreaderapp.ui_app.event

sealed interface NewsUiEvent {
    data class OnNewsClick(val title: String) : NewsUiEvent
    object OnBackClick : NewsUiEvent
    object Refresh : NewsUiEvent
}